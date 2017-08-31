/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rationalminds.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Vaibhav Singh
 */
public class Dictionary {

	public static PredictionModelNode monthPredictionTree = new PredictionModelNode();
	public static PredictionModelNode weekPredictionTree = new PredictionModelNode();
	public static PredictionModelNode patternPredictionTree = new PredictionModelNode();
	public static PredictionModelNode timePredictionTree = new PredictionModelNode();

	public static final List<String> WEEKDAY_FULL = new ArrayList<String>();
	public static final List<String> WEEKDAY_SHORT = new ArrayList<String>();
	public static final List<String> MONTH_FULL = new ArrayList<String>();
	public static final List<String> MONTH_SHORT = new ArrayList<String>();
	public static final List<String> PATTERN = new ArrayList<String>();
	public static final List<String> TIME_PATTERN = new ArrayList<String>();

	public static final int MONTH_LITERAL = 1;
	public static final int WEEKDAY_LITERAL = 2;
	public static final int DIGIT_LITERAL = 3;
	public static final int THREE_DIGIT_LITERAL = 4;
	public static final int FOUR_DIGIT_LITERAL = 5;
	public static final int DATE_PHRASE = 6;
	public static final int NOT_DATE_LITERAL = -1;

	/* only used to print tree on console */
	public static final int HOROZONTAL_PRINT_GAP = 3;
	public static final int VERTICAL_PRINT_GAP = 2;
	public static final String TERMINATION_SYMBOL = "^";

	static {
		WEEKDAY_FULL.add("sunday");
		WEEKDAY_FULL.add("monday");
		WEEKDAY_FULL.add("tuesday");
		WEEKDAY_FULL.add("wednesday");
		WEEKDAY_FULL.add("thursday");
		WEEKDAY_FULL.add("friday");
		WEEKDAY_FULL.add("saturday");

		WEEKDAY_SHORT.add("sun");
		WEEKDAY_SHORT.add("mon");
		WEEKDAY_SHORT.add("tue");
		WEEKDAY_SHORT.add("wed");
		WEEKDAY_SHORT.add("thu");
		WEEKDAY_SHORT.add("fri");
		WEEKDAY_SHORT.add("sat");

		MONTH_FULL.add("january");
		MONTH_FULL.add("february");
		MONTH_FULL.add("march");
		MONTH_FULL.add("april");
		MONTH_FULL.add("may");
		MONTH_FULL.add("june");
		MONTH_FULL.add("july");
		MONTH_FULL.add("august");
		MONTH_FULL.add("september");
		MONTH_FULL.add("october");
		MONTH_FULL.add("november");
		MONTH_FULL.add("december");

		MONTH_SHORT.add("jan");
		MONTH_SHORT.add("feb");
		MONTH_SHORT.add("mar");
		MONTH_SHORT.add("apr");
		MONTH_SHORT.add("may");
		MONTH_SHORT.add("jun");
		MONTH_SHORT.add("jul");
		MONTH_SHORT.add("aug");
		MONTH_SHORT.add("sep");
		MONTH_SHORT.add("oct");
		MONTH_SHORT.add("nov");
		MONTH_SHORT.add("dec");

		PATTERN.add("DDDD*DD*DD");
		PATTERN.add("DDDD*D*DD");
		PATTERN.add("DDDD*DD*D");
		PATTERN.add("DDDD*D*D");
		PATTERN.add("DD*DD*DD");
		PATTERN.add("DD*D*DD");
		PATTERN.add("DD*DD*D");
		PATTERN.add("DD*D*D");
		PATTERN.add("DD*DD*DDDD");
		PATTERN.add("D*DD*DDDD");
		PATTERN.add("DD*D*DDDD");
		PATTERN.add("D*D*DDDD");
		PATTERN.add("DD*DD*DD");
		PATTERN.add("D*DD*DD");
		PATTERN.add("DD*D*DD");
		PATTERN.add("D*D*DD");
		PATTERN.add("DDDD*M*DD");
		PATTERN.add("DDDD*M*D");
		PATTERN.add("DD*M*DD");
		PATTERN.add("DD*M*D");
		PATTERN.add("DD*M*DDDD");
		PATTERN.add("D*M*DDDD");
		PATTERN.add("DD*M*DD");
		PATTERN.add("D*M*DD");
		PATTERN.add("M*DD*DDDD");
		PATTERN.add("M*D*DDDD");
		PATTERN.add("M*DD*DD");
		PATTERN.add("M*D*DD");

		TIME_PATTERN.add("DD:DD:DD");
		TIME_PATTERN.add("DD:DD:DD am");
		TIME_PATTERN.add("DD:DD:DD pm");
		TIME_PATTERN.add("DD:DD:DD.DDD");
		TIME_PATTERN.add("DD:DD:DD,DDD");
		TIME_PATTERN.add("DD:DD:DD.DDD am");
		TIME_PATTERN.add("DD:DD:DD.DDD pm");
		TIME_PATTERN.add("DD:DD:DD,DDD am");
		TIME_PATTERN.add("DD:DD:DD,DDD pm");

		monthPredictionTree.level = 0;
		monthPredictionTree.charcter = '0';

		patternPredictionTree.level = 0;
		patternPredictionTree.charcter = '0';

		timePredictionTree.level = 0;
		timePredictionTree.charcter = '0';

		buildTree(MONTH_FULL, monthPredictionTree);
		buildTree(MONTH_SHORT, monthPredictionTree);
		buildTree(PATTERN, patternPredictionTree);
		buildTree(TIME_PATTERN, timePredictionTree);

	}

	public static void buildTree(List<String> itemList, PredictionModelNode parentNode) {
		for (String s : itemList) {
			PredictionModelNode parent = parentNode;
			parentNode.charcter = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				PredictionModelNode node = parent.getChild(c);
				if (node == null) {
					PredictionModelNode child = new PredictionModelNode();
					child.charcter = c;
					if (i + 1 == s.length()) {
						child.explictDateFragment = true;
					}
					parent.addChild(child);
					parent = child;
				} else {
					if (i + 1 == s.length()) {
						node.explictDateFragment = true;
					}
					parent = node;
				}
			}
		}
	}
	
	public static String getProbables(PredictionModelNode tree) {
		if(tree==null) {
			return "";
		}
		List<PredictionModelNode> kids=tree.childern;
		String pattern=tree.charcter+" -->";
		for(PredictionModelNode kid:kids) {
			pattern = pattern +" "+ kid.charcter;
		}
		return pattern;
	}

	public static void printTree(PredictionModelNode tree) {

		tree = sortTree(tree);

		DisplayObject displyObject = displayBuilder(new DisplayObject(), tree);

		StringBuffer printBuffer = new StringBuffer();

		String line = getBlankLine(displyObject.width);

		int prevLevel = 0;
		for (TreeChar tc : displyObject.displayBuffer) {
			if (prevLevel == tc.level) {
				line = line.substring(0, tc.leftSpace) + tc.symbol + line.substring(tc.leftSpace + tc.symbol.length());
			} else {
				line = line.replaceAll("\\s+$", "") + System.lineSeparator();
				printBuffer.append(line);
				line = getBlankLine(displyObject.width);
				line = line.substring(0, tc.leftSpace) + tc.symbol + line.substring(tc.leftSpace + tc.symbol.length());
				prevLevel = tc.level;
			}
		}
		printBuffer.append(line);
		System.out.println(printBuffer.toString());

	}

	/**
	 * 
	 * @param disply
	 * @param tree
	 * @return
	 */
	private static DisplayObject displayBuilder(DisplayObject display, PredictionModelNode tree) {
		if (isFamilyUnit(tree)) {
			return familyDisplayBuilder(tree);
		}

		DisplayObject horizontalDisply = new DisplayObject();
		for (PredictionModelNode kid : tree.childern) {
			DisplayObject recusiveDisplay = displayBuilder(horizontalDisply, kid);
			horizontalDisply = adjustHorizontalDisplay(horizontalDisply, recusiveDisplay);
		}

		Collections.sort(horizontalDisply.displayBuffer);

		DisplayObject verticalDisplay = new DisplayObject();
		int[] dadJoints = new int[tree.childrenCount()];

		int j = 0;
		for (TreeChar tc : horizontalDisply.displayBuffer) {
			if (tc.level > 0 || j > dadJoints.length) {
				break;
			}
			if (!(tc.symbol.matches("/|\\\\|_|\\|") || "".equals(tc.symbol.trim()))) {
				dadJoints[j++] = tc.leftSpace;
			}
		}

		if (tree.childrenCount() == 0) {
			return horizontalDisply;
		}
		if (tree.childrenCount() == 1) {
			String s = tree.explictDateFragment ? String.valueOf(tree.charcter) + TERMINATION_SYMBOL : String.valueOf(tree.charcter);
			TreeChar tc = new TreeChar(s, dadJoints[0], 0);
			verticalDisplay.displayBuffer.add(tc);

		} else {
			Arrays.sort(dadJoints);
			String symbol = "_";
			int papaPosition = dadJoints.length % 2 == 0 ? 1 + (dadJoints[dadJoints.length - 1] + dadJoints[0]) / 2 : dadJoints[dadJoints.length / 2] + 1;
			// improve visualization
			papaPosition--;
			for (int k = dadJoints[0] + 1; k <= dadJoints[dadJoints.length - 1] + 1 - 2; k++) {
				TreeChar tc;
				if (k == papaPosition) {
					String s = "".equals(String.valueOf(tree.charcter).trim()) ? "$" : String.valueOf(tree.charcter);
					if (tree.explictDateFragment) {
						s = s + TERMINATION_SYMBOL;
					}
					tc = new TreeChar(s, k, 0);
				} else {
					tc = new TreeChar(symbol, k, 0);
				}
				verticalDisplay.displayBuffer.add(tc);
			}
		}

		for (int i = 0; i < dadJoints.length; i++) {
			for (int m = 0; m < VERTICAL_PRINT_GAP; m++) {
				TreeChar tc = new TreeChar("|", dadJoints[i], m + 1);
				verticalDisplay.displayBuffer.add(tc);
			}
		}

		display = adjustVerticalDisplay(horizontalDisply, verticalDisplay);

		return display;
	}

	private static DisplayObject adjustVerticalDisplay(DisplayObject horizontalDisply, DisplayObject verticalDisplay) {
		if (horizontalDisply.displayBuffer.size() == 0) {
			return verticalDisplay;
		}
		Collections.sort(verticalDisplay.displayBuffer);
		for (TreeChar tc : horizontalDisply.displayBuffer) {
			tc.level += VERTICAL_PRINT_GAP + 1;
			verticalDisplay.displayBuffer.add(tc);
		}
		verticalDisplay.width = horizontalDisply.width;
		return verticalDisplay;
	}

	/**
	 * 
	 * @param display
	 * @param kidDisplay
	 * @return
	 */
	private static DisplayObject adjustHorizontalDisplay(DisplayObject display, DisplayObject kidDisplay) {
		if (display.displayBuffer.size() == 0) {
			return kidDisplay;
		}

		int leftPos = display.width;
		for (TreeChar tc : kidDisplay.displayBuffer) {
			tc.leftSpace += (leftPos + HOROZONTAL_PRINT_GAP);
			int i = 0;
			int displayBufferSize = display.displayBuffer.size();
			for (TreeChar displayTc : display.displayBuffer) {
				if (displayBufferSize == i + 1) {
					display.displayBuffer.add(displayBufferSize, tc);
					break;
				} else if (displayTc.level <= tc.level) {
					i++;
					continue;
				}
				display.displayBuffer.add(i - 1, tc);
				break;
			}
		}
		display.height = display.height >= kidDisplay.height ? display.height : kidDisplay.height;
		display.width = display.width + kidDisplay.width + HOROZONTAL_PRINT_GAP;
		return display;

	}

	private static DisplayObject familyDisplayBuilder(PredictionModelNode tree) {
		DisplayObject displyObject = new DisplayObject();
		int kids = tree.childrenCount();
		String charVal = String.valueOf(tree.charcter);
		if (tree.explictDateFragment) {
			charVal = charVal + TERMINATION_SYMBOL;
		}
		if (kids == 1) {
			displyObject.displayBuffer.add(new TreeChar(charVal, 0, 0));
			displyObject.displayBuffer.add(new TreeChar("|", 0, 1));
			String kidChar = String.valueOf(tree.childern.get(0).charcter);
			displyObject.displayBuffer.add(new TreeChar(kidChar, 0, 2));
			displyObject.width = 1;
			displyObject.height = 3;
		} else if (kids % 2 == 0) {
			displyObject = evenKidCountTree(displyObject, tree);
		} else {
			displyObject = oddKidCountTree(displyObject, tree);
		}
		return displyObject;
	}

	/**
	 * 
	 * @param displayBuffer
	 * @param tree
	 * @return
	 */
	public static DisplayObject evenKidCountTree(DisplayObject displyObject, PredictionModelNode tree) {
		int kids = tree.childrenCount();
		int papaPosition = kids / 2;
		String charVal = String.valueOf(tree.charcter);
		if (tree.explictDateFragment) {
			charVal = charVal + TERMINATION_SYMBOL;
		}
		displyObject.displayBuffer.add(new TreeChar(charVal, papaPosition, 0));
		displyObject.height = papaPosition + 2;
		displyObject.width = kids + 1;
		// Iterate to add tree structure elements '/','|','\'
		for (int i = 0; i < kids / 2; i++) {
			// Iterate to add columns
			int markers = (i + 1) * 2;
			int coeffiecient = 1;
			for (int j = 0; j < markers; j++) {
				// reset the coefficient once halfway is crossed
				if (coeffiecient == markers / 2 + 1) {
					coeffiecient = 1;
				}
				// half of markers will be on left
				if (j < markers / 2) {
					// String symbol = "/";
					String symbol = "|";
					int leftpos = papaPosition - coeffiecient++;
					/*
					 * if (leftpos == papaPosition - 1 && i != 0) { symbol =
					 * "|"; }
					 */
					if (j == markers / 2 - 1) {
						symbol = "/";
					}
					displyObject.displayBuffer.add(new TreeChar(symbol, leftpos, i + 1));
				} else {
					int rightpos = papaPosition + coeffiecient++;
					// String symbol = "\\";
					String symbol = "|";
					/*
					 * if (rightpos == papaPosition + 1 && i != 0) { symbol =
					 * "|"; }
					 */
					if ((j == markers - 1 && i != 0) || markers < 3) {
						symbol = "\\";
					}
					displyObject.displayBuffer.add(new TreeChar(symbol, rightpos, i + 1));
				}
			}

		}
		// Iterate to add children to tree
		for (int i = 0; i < kids; i++) {
			String kidVal = String.valueOf(tree.childern.get(i).charcter);
			if (i < kids / 2) {
				displyObject.displayBuffer.add(new TreeChar(kidVal, i, kids / 2 + 1));
			} else {
				displyObject.displayBuffer.add(new TreeChar(kidVal, i + 1, kids / 2 + 1));
			}
		}
		return displyObject;
	}

	/**
	 * 
	 * @param displayBuffer
	 * @param tree
	 * @return
	 */
	public static DisplayObject oddKidCountTree(DisplayObject displyObject, PredictionModelNode tree) {
		int kids = tree.childrenCount();
		int papaPosition = (kids - 1) / 2;
		String charVal = String.valueOf(tree.charcter);
		if (tree.explictDateFragment) {
			charVal = charVal + TERMINATION_SYMBOL;
		}
		displyObject.displayBuffer.add(new TreeChar(charVal, papaPosition, 0));
		displyObject.height = papaPosition + 2;
		displyObject.width = kids;
		// Iterate to add tree structure elements '/','|','\'
		for (int i = 0; i < (kids - 1) / 2; i++) {
			// Iterate to add columns
			int markers = 2 * i + 3;
			int coeffiecient = 1;
			for (int j = 0; j < markers; j++) {
				// reset the coefficient once halfway is crossed
				if (coeffiecient == markers / 2 + 1) {
					coeffiecient = 1;
				}
				// half of markers will be on left
				if (j < markers / 2) {
					// String symbol = "/";
					String symbol = "|";
					int leftpos = papaPosition - coeffiecient++;
					/*
					 * if (leftpos == papaPosition - 1 && i != 0) { symbol =
					 * "|"; }
					 */
					if (j == markers / 2 - 1 && markers > 2) {
						symbol = "/";
					}
					displyObject.displayBuffer.add(new TreeChar(symbol, leftpos, i + 1));
				} else if (j == markers / 2) {
					displyObject.displayBuffer.add(new TreeChar("|", papaPosition, i + 1));
				} else {
					int rightpos = papaPosition + coeffiecient++;
					// String symbol = "\\";
					String symbol = "|";
					/*
					 * if (rightpos == papaPosition + 1 && i != 0) { symbol =
					 * "|"; }
					 */
					if (j == markers - 1 && markers > 2) {
						symbol = "\\";
					}
					displyObject.displayBuffer.add(new TreeChar(symbol, rightpos, i + 1));
				}
			}

		}
		// Iterate to add children to tree
		for (int i = 0; i < kids; i++) {
			String kidVal = String.valueOf(tree.childern.get(i).charcter);

			displyObject.displayBuffer.add(new TreeChar(kidVal, i, kids / 2 + 1));
		}
		return displyObject;
	}

	private static int getMaxTreeHeight(PredictionModelNode tree) {
		if (tree.hasChildern()) {
			int[] height = new int[tree.childrenCount()];
			for (int i = 0; i < tree.childrenCount(); i++) {
				PredictionModelNode child = tree.childern.get(i);
				height[i] = getMaxTreeHeight(child);
			}
			int max = height[0];
			for (int j = 1; j < height.length; j++) {
				if (max < height[j]) {
					max = height[j];
				}
			}
			return max + 1;
		}
		return 0;
	}

	public static PredictionModelNode sortTree(PredictionModelNode tree) {
		if (tree.hasChildern()) {
			for (PredictionModelNode child : tree.childern) {
				child = sortTree(child);
			}
			boolean swapped = true;
			if (tree.childrenCount() <= 1) {
				return tree;
			}
			while (swapped) {
				swapped = false;
				for (int i = 0; i < tree.childrenCount() - 1; i++) {
					int thisHeight = getMaxTreeHeight(tree.childern.get(i));
					int nextHeight = getMaxTreeHeight(tree.childern.get(i + 1));
					if (thisHeight < nextHeight) {
						swapped = true;
						PredictionModelNode tempChild = tree.childern.get(i);
						tree.childern.set(i, tree.childern.get(i + 1));
						tree.childern.set(i + 1, tempChild);
					}
				}
			}
			return tree;
		}
		return tree;
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	private static boolean isFamilyUnit(PredictionModelNode tree) {
		if (tree.hasChildern()) {
			for (PredictionModelNode node : tree.childern) {
				if (node.hasChildern()) {
					return false;
				}
			}
			return true;
		}
		return true;
	}

	private static String getBlankLine(int i) {
		String str = new String();
		str = " ";
		for (int j = 0; j < i; j++) {
			str = " " + str;
		}
		return str;
	}

}
