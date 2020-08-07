package ExtendedList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main
{
	public static void main(String[] args)
	{
		Integer[] ints = {100, 200, 300};
		Set<Integer> set = new HashSet<>(Arrays.asList(3, 4, 5));

		XList<Integer> list1 = new XList<>(1, 3, 9, 11);
		XList<Integer> list2 = XList.of(5, 6, 9);
		XList<Integer> list3 = new XList(ints);
		XList<Integer> list4 = XList.of(ints);
		XList<Integer> list5 = new XList(set);
		XList<Integer> list6 = XList.of(set);

		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);
		System.out.println(list5);
		System.out.println(list6);

		XList<String> slist1 = XList.ofChars("ala ma kota");
		XList<String> slist2 = XList.ofTokens("ala ma kota");
		XList<String> slist3 = XList.ofTokens("A-B-C", "-");

		System.out.println(slist1);
		System.out.println(slist2);
		System.out.println(slist3);

		List<Integer> m1 = list1.union(list2);
		System.out.println(m1);
		m1.add(11);
		System.out.println(m1);
		XList<Integer> m2 = (XList<Integer>) m1;
		XList<Integer> m3 = m2.union(ints).union(XList.of(4, 4));
		System.out.println(m2);
		System.out.println(m3);
		m3 = m3.union(set);
		System.out.println(m3);

		System.out.println(m3.diff(set));
		System.out.println(XList.of(set).diff(m3));

		XList<Integer> uniq = m3.unique();
		System.out.println(uniq);

		List<String> sa = Arrays.asList("a", "b");
		List<String> sb = Arrays.asList("X", "Y", "Z");
		XList<String> sc = XList.ofChars("12");
		XList toCombine = XList.of(sa, sb, sc);
		System.out.println(toCombine);
		XList<XList<String>> cres = toCombine.combine();
		System.out.println(cres);

		XList<String> j1 = cres.collect(list -> list.join());
		System.out.println(j1.join(" "));
		XList<String> j2 = cres.collect(list -> list.join("-"));
		System.out.println(j2.join(" "));

		XList<Integer> lmod = XList.of(1, 2, 8, 10, 11, 30, 3, 4);
		lmod.forEachWithIndex((e, i) -> lmod.set(i, e * 2));
		System.out.println(lmod);
		lmod.forEachWithIndex((e, i) ->
		{ if (i % 2 == 0) lmod.remove(e); });
		System.out.println(lmod);
		lmod.forEachWithIndex((e, i) ->
		{ if (i % 2 == 0) lmod.remove(i); });
		System.out.println(lmod);
	}
}
