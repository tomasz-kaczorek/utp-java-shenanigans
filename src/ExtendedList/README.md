#Extended List

Create a class:

    class XList<T>
    {
        static XList<String> ofChars(String s)                  //splits s into individual characters and returns result as a new XList
        static XList<String> ofTokens(String s, String p)       //splits s with an optional separator p and returns result as a new XList
        XList<T> union(Collection<T> c)                         //returns a union of this XList and a Collection as a new XList
        XList<T> diff(Collection<T> c)                          //returns a difference of this XList and a Collection as a new XList
        XList<T> unique()                                       //returns all unique elements of this XList as a new XList
        <E> XList<XList<E>> combine()                           //returns a cartesian product of this XList elements as a new XList
        <R> XList<R> collect(Function<T, R> f)                  //returns results of applying Function to this XList elements as a new XList
        String join(String separator)                           //returns all elements of this XList as a String, optional separator can be passed as argument
        void forEachWithIndex(BiConsumer<T, Integer> consumer)  //mutates this XList with access to both the element and its index
    }

additionally provide necessary constructors and static *of* functions that allows for XList creation from other collections, arrays and initializer lists.

Following test program:

    import java.util.*;

    public class Main {
      public static void main(String[] args) {
        Integer[] ints = { 100, 200, 300 };
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
        
        XList<String> slist1 = XList.charsOf("ala ma kota");
        XList<String> slist2 = XList.tokensOf("ala ma kota");
        XList<String> slist3 = XList.tokensOf("A-B-C", "-");
    
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
       
        List<String> sa = Arrays.asList( "a", "b");
        List<String> sb = Arrays.asList( "X", "Y", "Z" );
        XList<String> sc = XList.charsOf( "12" );
        XList toCombine = XList.of(sa, sb, sc);
        System.out.println(toCombine);
        XList<XList<String>> cres = toCombine.combine();
        System.out.println(cres);
    
        XList<String> j1 = cres.collect( list -> list.join());
        System.out.println(j1.join(" "));
        XList<String> j2 =cres.collect( list -> list.join("-"));
        System.out.println(j2.join(" "));
        
        XList<Integer> lmod = XList.of(1,2,8, 10, 11, 30, 3, 4);  
        lmod.forEachWithIndex( (e, i) -> lmod.set(i, e*2));
        System.out.println(lmod);
        lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(e); } );
        System.out.println(lmod);
        lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(i); } );
        System.out.println(lmod); 
      }
    }
should print:

    [1, 3, 9, 11]
    [5, 6, 9]
    [100, 200, 300]
    [100, 200, 300]
    [3, 4, 5]
    [3, 4, 5]
    [a, l, a,  , m, a,  , k, o, t, a]
    [ala, ma, kota]
    [A, B, C]
    [1, 3, 9, 11, 5, 6, 9]
    [1, 3, 9, 11, 5, 6, 9, 11]
    [1, 3, 9, 11, 5, 6, 9, 11]
    [1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4]
    [1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4, 3, 4, 5]
    [1, 9, 11, 6, 9, 11, 100, 200, 300]
    []
    [1, 3, 9, 11, 5, 6, 100, 200, 300, 4]
    [[a, b], [X, Y, Z], [1, 2]]
    [[a, X, 1], [b, X, 1], [a, Y, 1], [b, Y, 1], [a, Z, 1], [b, Z, 1], [a, X, 2], [b, X, 2], [a, Y, 2], [b, Y, 2], [a, Z, 2], [b, Z, 2]]
    aX1 bX1 aY1 bY1 aZ1 bZ1 aX2 bX2 aY2 bY2 aZ2 bZ2
    a-X-1 b-X-1 a-Y-1 b-Y-1 a-Z-1 b-Z-1 a-X-2 b-X-2 a-Y-2 b-Y-2 a-Z-2 b-Z-2
    [2, 4, 16, 20, 22, 60, 6, 8]
    [4, 16, 22, 60, 8]
    [16, 22, 60, 8]
