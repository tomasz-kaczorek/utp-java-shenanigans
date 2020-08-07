package Maybe;

public class Main
{
	public static void test()
	{
		String s = "aaa";
		Maybe<String> m1 = Maybe.of(s);
		System.out.println(m1);
		s = null;
		Maybe<String> m2 = Maybe.of(s);
		System.out.println(m2);

		Integer num = null;
		Maybe<Integer> m4 = Maybe.of(num);
		if (num != null) System.out.println(num);
		m4.ifPresent(n -> System.out.println(n));
		m4.ifPresent(System.out::println);

		Maybe<Integer> m5 = Maybe.of(10);
		m5.ifPresent(System.out::println);

		Maybe<Integer> m6 = m5.map(n -> n + 10);
		System.out.println(m6);

		System.out.println(m6.get());
		try
		{
			System.out.println(m4.get());
		}
		catch (Exception exc)
		{
			System.out.println(exc);
		}

		String snum = null;
		if (num != null) snum = "Wartość wynosi: " + num;
		if (snum != null) System.out.println(snum);
		else System.out.println("Wartość niedostępna");

		String res = Maybe.of(num).map(n -> "Wartość wynosi: " + n)
			.orElse("Wartość niedostępna");
		System.out.println(res);

		String txt = "Pies";
		String msg = "";

		if (txt != null && txt.length() > 0)
		{
			msg = txt;
		} else
		{
			msg = "Txt is null or empty";
		}

		msg = Maybe.of(txt)
			.filter(t -> t.length() > 0)
			.orElse("Txt is null or empty");
		System.out.println(msg);
	}

	public static void main(String[] args)
	{
		test();
	}
}
