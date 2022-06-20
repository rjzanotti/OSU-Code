import java.util.Arrays;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to test arrays, references, and arrays of references.
 *
 * @author Put your name here
 *
 */
public final class ArraysAndReferences {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ArraysAndReferences() {
    }

    /**
     * Computes the product of the {@code NaturalNumber}s in the given array.
     *
     * @param nnArray
     *            the array
     * @return the product of the numbers in the given array
     * @requires nnArray.length > 0
     * @ensures <pre>
     * productOfArrayElements =
     *    [nnArray[0] * nnArray[1] * ... * nnArray[nnArray.length-1]]
     * </pre>
     */
    private static NaturalNumber productOfArrayElements(
            NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";
        NaturalNumber total = new NaturalNumber2(1);
        for (int i = 0; i < nnArray.length; i++) {
            total.multiply(nnArray[i]);
        }
        return total;
    }

    /**
     * Replaces each element of {@code nnArray} with the partial product of all
     * the elements in the incoming array, up to and including the current
     * element.
     *
     * @param nnArray
     *            the array
     * @updates nnArray
     * @requires nnArray.length > 0
     * @ensures <pre>
     * for all i: integer where (0 <= i < nnArray.length)
     *   (nnArray[i] = [#nnArray[0] * #nnArray[1] * ... * #nnArray[i]])
     * </pre>
     */
    private static void computePartialProducts(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";
        NaturalNumber total = new NaturalNumber2(1);
        for (int i = 0; i < nnArray.length; i++) {
            total.multiply(nnArray[i]);
            nnArray[i].copyFrom(total);
        }
    }

    /**
     * Creates and returns a new array of {@code NaturalNumber}s, of the same
     * size of the given array, containing the partial products of the elements
     * of the given array.
     *
     * @param nnArray
     *            the array
     * @return the array of partial products of the elements of the given array
     * @requires nnArray.length > 0
     * @ensures <pre>
     * partialProducts.length = nnArray.length  and
     *  for all i: integer where (0 <= i < partialProducts.length)
     *    (partialProducts[i] = [nnArray[0] * nnArray[1] * ... * nnArray[i]])
     * </pre>
     */
    private static NaturalNumber[] partialProducts(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        NaturalNumber[] totalArray = new NaturalNumber[nnArray.length];
        NaturalNumber count = new NaturalNumber2(1);
        NaturalNumber total = new NaturalNumber2(1);
        for (int i = 0; i < nnArray.length; i++) {
            totalArray[i] = count.newInstance();
            total.multiply(nnArray[i]);
            totalArray[i].copyFrom(total);
        }
        return totalArray;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader1L in = new SimpleReader1L();
        /*
         * Initialize an array of NaturalNumbers with values 1 through 42.
         */
        NaturalNumber[] array = new NaturalNumber[5];
        NaturalNumber count = new NaturalNumber2(1);
        for (int i = 0; i < array.length; i++) {
            array[i] = count.newInstance();
            array[i].copyFrom(count);
            count.increment();
        }
        /*
         * Compute and output the product of the numbers in the array (should be
         * 42!, i.e., the factorial of 42).
         */
        out.print(
                "Enter 1 to find product of array elements, 2 to compute partial, and 3 for array of partial: ");
        int choice = in.nextInteger();

        if (choice == 1) {
            NaturalNumber product = productOfArrayElements(array);
            out.println(product);
        } else if (choice == 2) {
            computePartialProducts(array);
            for (int i = 0; i < array.length; i++) {
                out.print(i + " term: " + array[i] + "|");
            }
        } else if (choice == 3) {
            out.println(Arrays.toString(partialProducts(array)));
        } else {
            out.println("Invalid Choice");
        }

        out.close();
        in.close();
    }
}