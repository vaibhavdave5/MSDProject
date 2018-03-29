package utils;

import algorithms.Result;
import algorithms.SimilaritySnippet;
import driver.CodeSnippets;
import driver.FilePair;
import org.junit.Test;
import parser.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Util class
 * @author Shail Shah
 */
public class FileUtilsTest {

	/**
	 * get file string from the start
	 */
	@Test
	public void getFileStringNormalStart() {
		File file = new File("utilTestData.c");
		int start = 1;
		int end = 3;
		String fileString = FileUtils.getFileString(file, start, end);
		String expectedfileString = "1. /*\n2.  * Generic cpu hotunplug interrupt migration code copied from the\n3.  * arch/arm implementation\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string from the middle
	 */
	@Test
	public void getFileStringNormalMiddle() {
		File file = new File("utilTestData.c");
		int start = 2;
		int end = 3;
		String fileString = FileUtils.getFileString(file, start, end);
		String expectedfileString = "2.  * Generic cpu hotunplug interrupt migration code copied from the\n" +
				"3.  * arch/arm implementation\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string with a negative starting line
	 */
	@Test
	public void getFileStringNormalNegativeStart() {
		File file = new File("utilTestData.c");
		int start = -1;
		int end = 3;
		String fileString = FileUtils.getFileString(file, start, end);
		String expectedfileString = "1. /*\n2.  * Generic cpu hotunplug interrupt migration code copied from the\n3.  * arch/arm implementation\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string with a very big end line
	 */
	@Test
	public void getFileStringNormalExceedFilel() {
		File file = new File("utilTestData.c");
		int start = -1;
		int end = 300;
		String fileString = FileUtils.getFileString(file, start, end);
		String expectedfileString = "1. /*\n" +
				"2.  * Generic cpu hotunplug interrupt migration code copied from the\n" +
				"3.  * arch/arm implementation\n" +
				"4.  *\n" +
				"5.  * Copyright (C) Russell King\n" +
				"6.  *\n" +
				"7.  * This program is free software; you can redistribute it and/or modify\n" +
				"8.  * it under the terms of the GNU General Public License version 2 as\n" +
				"9.  * published by the Free Software Foundation.\n" +
				"10.  */\n" +
				"11. #include <linux/interrupt.h>\n" +
				"12. #include <linux/ratelimit.h>\n" +
				"13. #include <linux/irq.h>\n" +
				"14. \n" +
				"15. #include \"internals.h\"\n" +
				"16. \n" +
				"17. /* For !GENERIC_IRQ_EFFECTIVE_AFF_MASK this looks at general affinity mask */\n" +
				"18. static inline bool irq_needs_fixup(struct irq_data *d)\n" +
				"19. {\n" +
				"20. \tconst struct cpumask *m = irq_data_get_effective_affinity_mask(d);\n" +
				"21. \tunsigned int cpu = smp_processor_id();\n" +
				"22. \n" +
				"23. #ifdef CONFIG_GENERIC_IRQ_EFFECTIVE_AFF_MASK\n" +
				"24. \t/*\n" +
				"25. \t * The cpumask_empty() check is a workaround for interrupt chips,\n" +
				"26. \t * which do not implement effective affinity, but the architecture has\n" +
				"27. \t * enabled the config switch. Use the general affinity mask instead.\n" +
				"28. \t */\n" +
				"29. \tif (cpumask_empty(m))\n" +
				"30. \t\tm = irq_data_get_affinity_mask(d);\n" +
				"31. \n" +
				"32. \t/*\n" +
				"33. \t * Sanity check. If the mask is not empty when excluding the outgoing\n" +
				"34. \t * CPU then it must contain at least one online CPU. The outgoing CPU\n" +
				"35. \t * has been removed from the online mask already.\n" +
				"36. \t */\n" +
				"37. \tif (cpumask_any_but(m, cpu) < nr_cpu_ids &&\n" +
				"38. \t    cpumask_any_and(m, cpu_online_mask) >= nr_cpu_ids) {\n" +
				"39. \t\t/*\n" +
				"40. \t\t * If this happens then there was a missed IRQ fixup at some\n" +
				"41. \t\t * point. Warn about it and enforce fixup.\n" +
				"42. \t\t */\n" +
				"43. \t\tpr_warn(\"Eff. affinity %*pbl of IRQ %u contains only offline CPUs after offlining CPU %u\\n\",\n" +
				"44. \t\t\tcpumask_pr_args(m), d->irq, cpu);\n" +
				"45. \t\treturn true;\n" +
				"46. \t}\n" +
				"47. #endif\n" +
				"48. \treturn cpumask_test_cpu(cpu, m);\n" +
				"49. }\n" +
				"50. \n" +
				"51. static bool migrate_one_irq(struct irq_desc *desc)\n" +
				"52. {\n" +
				"53. \tstruct irq_data *d = irq_desc_get_irq_data(desc);\n" +
				"54. \tstruct irq_chip *chip = irq_data_get_irq_chip(d);\n" +
				"55. \tbool maskchip = !irq_can_move_pcntxt(d) && !irqd_irq_masked(d);\n" +
				"56. \tconst struct cpumask *affinity;\n" +
				"57. \tbool brokeaff = false;\n" +
				"58. \tint err;\n" +
				"59. \n" +
				"60. \t/*\n" +
				"61. \t * IRQ chip might be already torn down, but the irq descriptor is\n" +
				"62. \t * still in the radix tree. Also if the chip has no affinity setter,\n" +
				"63. \t * nothing can be done here.\n" +
				"64. \t */\n" +
				"65. \tif (!chip || !chip->irq_set_affinity) {\n" +
				"66. \t\tpr_debug(\"IRQ %u: Unable to migrate away\\n\", d->irq);\n" +
				"67. \t\treturn false;\n" +
				"68. \t}\n" +
				"69. \n" +
				"70. \t/*\n" +
				"71. \t * No move required, if:\n" +
				"72. \t * - Interrupt is per cpu\n" +
				"73. \t * - Interrupt is not started\n" +
				"74. \t * - Affinity mask does not include this CPU.\n" +
				"75. \t *\n" +
				"76. \t * Note: Do not check desc->action as this might be a chained\n" +
				"77. \t * interrupt.\n" +
				"78. \t */\n" +
				"79. \tif (irqd_is_per_cpu(d) || !irqd_is_started(d) || !irq_needs_fixup(d)) {\n" +
				"80. \t\t/*\n" +
				"81. \t\t * If an irq move is pending, abort it if the dying CPU is\n" +
				"82. \t\t * the sole target.\n" +
				"83. \t\t */\n" +
				"84. \t\tirq_fixup_move_pending(desc, false);\n" +
				"85. \t\treturn false;\n" +
				"86. \t}\n" +
				"87. \n" +
				"88. \t/*\n" +
				"89. \t * Complete an eventually pending irq move cleanup. If this\n" +
				"90. \t * interrupt was moved in hard irq context, then the vectors need\n" +
				"91. \t * to be cleaned up. It can't wait until this interrupt actually\n" +
				"92. \t * happens and this CPU was involved.\n" +
				"93. \t */\n" +
				"94. \tirq_force_complete_move(desc);\n" +
				"95. \n" +
				"96. \t/*\n" +
				"97. \t * If there is a setaffinity pending, then try to reuse the pending\n" +
				"98. \t * mask, so the last change of the affinity does not get lost. If\n" +
				"99. \t * there is no move pending or the pending mask does not contain\n" +
				"100. \t * any online CPU, use the current affinity mask.\n" +
				"101. \t */\n" +
				"102. \tif (irq_fixup_move_pending(desc, true))\n" +
				"103. \t\taffinity = irq_desc_get_pending_mask(desc);\n" +
				"104. \telse\n" +
				"105. \t\taffinity = irq_data_get_affinity_mask(d);\n" +
				"106. \n" +
				"107. \t/* Mask the chip for interrupts which cannot move in process context */\n" +
				"108. \tif (maskchip && chip->irq_mask)\n" +
				"109. \t\tchip->irq_mask(d);\n" +
				"110. \n" +
				"111. \tif (cpumask_any_and(affinity, cpu_online_mask) >= nr_cpu_ids) {\n" +
				"112. \t\t/*\n" +
				"113. \t\t * If the interrupt is managed, then shut it down and leave\n" +
				"114. \t\t * the affinity untouched.\n" +
				"115. \t\t */\n" +
				"116. \t\tif (irqd_affinity_is_managed(d)) {\n" +
				"117. \t\t\tirqd_set_managed_shutdown(d);\n" +
				"118. \t\t\tirq_shutdown(desc);\n" +
				"119. \t\t\treturn false;\n" +
				"120. \t\t}\n" +
				"121. \t\taffinity = cpu_online_mask;\n" +
				"122. \t\tbrokeaff = true;\n" +
				"123. \t}\n" +
				"124. \t/*\n" +
				"125. \t * Do not set the force argument of irq_do_set_affinity() as this\n" +
				"126. \t * disables the masking of offline CPUs from the supplied affinity\n" +
				"127. \t * mask and therefore might keep/reassign the irq to the outgoing\n" +
				"128. \t * CPU.\n" +
				"129. \t */\n" +
				"130. \terr = irq_do_set_affinity(d, affinity, false);\n" +
				"131. \tif (err) {\n" +
				"132. \t\tpr_warn_ratelimited(\"IRQ%u: set affinity failed(%d).\\n\",\n" +
				"133. \t\t\t\t    d->irq, err);\n" +
				"134. \t\tbrokeaff = false;\n" +
				"135. \t}\n" +
				"136. \n" +
				"137. \tif (maskchip && chip->irq_unmask)\n" +
				"138. \t\tchip->irq_unmask(d);\n" +
				"139. \n" +
				"140. \treturn brokeaff;\n" +
				"141. }\n" +
				"142. \n" +
				"143. /**\n" +
				"144.  * irq_migrate_all_off_this_cpu - Migrate irqs away from offline cpu\n" +
				"145.  *\n" +
				"146.  * The current CPU has been marked offline.  Migrate IRQs off this CPU.\n" +
				"147.  * If the affinity settings do not allow other CPUs, force them onto any\n" +
				"148.  * available CPU.\n" +
				"149.  *\n" +
				"150.  * Note: we must iterate over all IRQs, whether they have an attached\n" +
				"151.  * action structure or not, as we need to get chained interrupts too.\n" +
				"152.  */\n" +
				"153. void irq_migrate_all_off_this_cpu(void)\n" +
				"154. {\n" +
				"155. \tstruct irq_desc *desc;\n" +
				"156. \tunsigned int irq;\n" +
				"157. \n" +
				"158. \tfor_each_active_irq(irq) {\n" +
				"159. \t\tbool affinity_broken;\n" +
				"160. \n" +
				"161. \t\tdesc = irq_to_desc(irq);\n" +
				"162. \t\traw_spin_lock(&desc->lock);\n" +
				"163. \t\taffinity_broken = migrate_one_irq(desc);\n" +
				"164. \t\traw_spin_unlock(&desc->lock);\n" +
				"165. \n" +
				"166. \t\tif (affinity_broken) {\n" +
				"167. \t\t\tpr_warn_ratelimited(\"IRQ %u: no longer affine to CPU%u\\n\",\n" +
				"168. \t\t\t\t\t    irq, smp_processor_id());\n" +
				"169. \t\t}\n" +
				"170. \t}\n" +
				"171. }\n" +
				"172. \n" +
				"173. static void irq_restore_affinity_of_irq(struct irq_desc *desc, unsigned int cpu)\n" +
				"174. {\n" +
				"175. \tstruct irq_data *data = irq_desc_get_irq_data(desc);\n" +
				"176. \tconst struct cpumask *affinity = irq_data_get_affinity_mask(data);\n" +
				"177. \n" +
				"178. \tif (!irqd_affinity_is_managed(data) || !desc->action ||\n" +
				"179. \t    !irq_data_get_irq_chip(data) || !cpumask_test_cpu(cpu, affinity))\n" +
				"180. \t\treturn;\n" +
				"181. \n" +
				"182. \tif (irqd_is_managed_and_shutdown(data)) {\n" +
				"183. \t\tirq_startup(desc, IRQ_RESEND, IRQ_START_COND);\n" +
				"184. \t\treturn;\n" +
				"185. \t}\n" +
				"186. \n" +
				"187. \t/*\n" +
				"188. \t * If the interrupt can only be directed to a single target\n" +
				"189. \t * CPU then it is already assigned to a CPU in the affinity\n" +
				"190. \t * mask. No point in trying to move it around.\n" +
				"191. \t */\n" +
				"192. \tif (!irqd_is_single_target(data))\n" +
				"193. \t\tirq_set_affinity_locked(data, affinity, false);\n" +
				"194. }\n" +
				"195. \n" +
				"196. /**\n" +
				"197.  * irq_affinity_online_cpu - Restore affinity for managed interrupts\n" +
				"198.  * @cpu:\tUpcoming CPU for which interrupts should be restored\n" +
				"199.  */\n" +
				"200. int irq_affinity_online_cpu(unsigned int cpu)\n" +
				"201. {\n" +
				"202. \tstruct irq_desc *desc;\n" +
				"203. \tunsigned int irq;\n" +
				"204. \n" +
				"205. \tirq_lock_sparse();\n" +
				"206. \tfor_each_active_irq(irq) {\n" +
				"207. \t\tdesc = irq_to_desc(irq);\n" +
				"208. \t\traw_spin_lock_irq(&desc->lock);\n" +
				"209. \t\tirq_restore_affinity_of_irq(desc, cpu);\n" +
				"210. \t\traw_spin_unlock_irq(&desc->lock);\n" +
				"211. \t}\n" +
				"212. \tirq_unlock_sparse();\n" +
				"213. \n" +
				"214. \treturn 0;\n" +
				"215. }\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * Test getFileString() for a java file as input
	 */
	@Test
	public void getFileStringJava() {
		File file = new File("utilTestData.java");
		int start = 925;
		int end = 931;
		String fileString = FileUtils.getFileString(file, start, end);
		String expectedfileString = "925. \t\tpublic void add(int index, E e) {\n" +
				"926. \t\t\trangeCheckForAdd(index);\n" +
				"927. \t\t\tcheckForComodification();\n" +
				"928. \t\t\tparent.add(parentOffset + index, e);\n" +
				"929. \t\t\tthis.modCount = parent.modCount;\n" +
				"930. \t\t\tthis.size++;\n" +
				"931. \t\t}\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string from a non-existant file
	 */
	@Test
	public void getFileStringNormalNonExistantFile() {
		int start = -1;
		int end = 3;
		File file = new File("DoesNotExist.c");
		String fileString = FileUtils.getFileString(file, start, end);
		assertEquals("", fileString);
	}

	/**
	 * Test for writing to a file and then deleting it
	 * @throws IOException
	 */
	@Test
	public void writeToFileNormal() throws IOException {
		String filename = "testWrite.txt";
		String message = "Testing file\nNewline";
		FileUtils.writeToFile(filename, message);
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}

		sb.setLength(sb.length()-1);
		assertEquals(message, sb.toString());

		br.close();
		Files.deleteIfExists(Paths.get(filename));
	}

	@Test(expected = Exception.class)
	public void getReportTestNull() {
		CodeSnippets cs = new CodeSnippets(1, 2);
		String report = FileUtils.getReport(cs);
	}

	@Test
	public void getReportTestNormal() {
		CodeSnippets cs = new CodeSnippets(101, 102);

		File file1 = new File("sample.c");
		File file2 = new File("sample2.c");

		Set<SimilaritySnippet> set = new TreeSet<>();
		Node node1 = new Node(1, 40, 3, "className1");
		Node node2 = new Node(1, 50, 3, "className2" );
		List<SimilaritySnippet> snippetList = new ArrayList<>();
		snippetList.add(new SimilaritySnippet(node1, node2));
		Result result =  new Result(0.6, snippetList);
		FilePair filePair = new FilePair(file1, file2);
		filePair.setResult(result);
		List<FilePair> filePairList = new ArrayList<>();
		filePairList.add(filePair);
		cs.setFilePairList(filePairList);

		String report = FileUtils.getReport(cs);
 
		String date = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault()).format(new Date()) + "\n";

		String expectedReport = date + "Report for Darshan and Saman\n" +
				"sample.c and sample2.c are suspected to be similar. \n" +
				"There is a 60.0% match.\n" +
				"Student A's sample.c\n" +
				"1. /* This function takes last element as pivot, places\n" +
				"2.    the pivot element at its correct position in sorted\n" +
				"3. \tarray, and places all smaller (smaller than pivot)\n" +
				"4.    to left of pivot and all greater elements to right\n" +
				"5.    of pivot */\n" +
				"6. int partition (int arr[], int low, int high)\n" +
				"7. {\n" +
				"8. \tint pivot = arr[high];    // pivot\n" +
				"9. \tint i = (low - 1);  // Index of smaller element\n" +
				"10. \n" +
				"11. \tfor (int j = low; j <= high- 1; j++)\n" +
				"12. \t{\n" +
				"13. \t\t// If current element is smaller than or\n" +
				"14. \t\t// equal to pivot\n" +
				"15. \t\tif (arr[j] <= pivot)\n" +
				"16. \t\t{\n" +
				"17. \t\t\ti++;    // increment index of smaller element\n" +
				"18. \t\t\tswap(&arr[i], &arr[j]);\n" +
				"19. \t\t}\n" +
				"20. \t}\n" +
				"21. \tswap(&arr[i + 1], &arr[high]);\n" +
				"22. \treturn (i + 1);\n" +
				"23. }\n" +
				"24. \n" +
				"25. /* The main function that implements QuickSort\n" +
				"26.  arr[] --> Array to be sorted,\n" +
				"27.   low  --> Starting index,\n" +
				"28.   high  --> Ending index */\n" +
				"29. void quickSort(int arr[], int low, int high)\n" +
				"30. {\n" +
				"31. \tif (low < high)\n" +
				"32. \t{\n" +
				"33. \t\t/* pi is partitioning index, arr[p] is now\n" +
				"34. \t\t   at right place */\n" +
				"35. \t\tint pi = partition(arr, low, high);\n" +
				"36. \n" +
				"37. \t\t// Separately sort elements before\n" +
				"38. \t\t// partition and after partition\n" +
				"39. \t\tquickSort(arr, low, pi - 1);\n" +
				"40. \t\tquickSort(arr, pi + 1, high);\n" +
				"Student B's sample2.c\n" +
				"1. ///* C program for Merge Sort */\n" +
				"2. //#include<stdlib.h>\n" +
				"3. //#include<stdio.h>\n" +
				"4. \n" +
				"5. // Merges two subarrays of arr[].\n" +
				"6. // First subarray is arr[l..m]\n" +
				"7. // Second subarray is arr[m+1..r]\n" +
				"8. void merge(int arr[], int l, int m, int r)\n" +
				"9. {\n" +
				"10.     int i, j, k;\n" +
				"11.     int n1 = m - l + 1;\n" +
				"12.     int n2 =  r - m;\n" +
				"13. \n" +
				"14.     /* create temp arrays */\n" +
				"15.     int L[n1], R[n2];\n" +
				"16. \n" +
				"17.     /* Copy data to temp arrays L[] and R[] */\n" +
				"18.     for (i = 0; i < n1; i++)\n" +
				"19.         L[i] = arr[l + i];\n" +
				"20.     for (j = 0; j < n2; j++)\n" +
				"21.         R[j] = arr[m + 1+ j];\n" +
				"22. \n" +
				"23.     /* Merge the temp arrays back into arr[l..r]*/\n" +
				"24.     i = 0; // Initial index of first subarray\n" +
				"25.     j = 0; // Initial index of second subarray\n" +
				"26.     k = l; // Initial index of merged subarray\n" +
				"27.     while (i < n1 && j < n2)\n" +
				"28.     {\n" +
				"29.         if (L[i] <= R[j])\n" +
				"30.         {\n" +
				"31.             arr[k] = L[i];\n" +
				"32.             i++;\n" +
				"33.         }\n" +
				"34.         else\n" +
				"35.         {\n" +
				"36.             arr[k] = R[j];\n" +
				"37.             j++;\n" +
				"38.         }\n" +
				"39.         k++;\n" +
				"40.     }\n" +
				"41. \n" +
				"42.     /* Copy the remaining elements of L[], if there\n" +
				"43.        are any */\n" +
				"44.     while (i < n1)\n" +
				"45.     {\n" +
				"46.         arr[k] = L[i];\n" +
				"47.         i++;\n" +
				"48.         k++;\n" +
				"49.     }\n" +
				"50. \n";
		assertEquals(expectedReport, report);
	}
}