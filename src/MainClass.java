import com.sun.deploy.util.StringUtils;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by cgev on 10/6/16.
 */
public class MainClass {
    private static final int[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };
    private static final int[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };
    private static final int[] PC2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };
    private static final int[] E = {
            32, 1,  2,  3,  4,  5,
            4,  5,  6,  7,  8,  9,
            8,  9,  10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };

    private static final int[][] S = { {
            14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
            0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
            4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
            15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
    }, {
            15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
            3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
            0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
            13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
    }, {
            10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
            13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
            13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
            1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
    }, {
            7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
            13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
            10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
            3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
    }, {
            2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
            14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
            4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
            11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
    }, {
            12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
            10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
            9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
            4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
    }, {
            4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
            13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
            1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
            6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
    }, {
            13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
            1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
            7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
            2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
    } };
    private static final byte[] P = {
            16, 7,  20, 21,  29, 12, 28, 17,
            1,  15, 23, 26, 5,  18, 31, 10,
            2,  8,  24, 14, 32, 27, 3,  9,
            19, 13, 30, 6, 22, 11, 4,  25
    };
    private static final byte[] FP = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };
    private static final byte[] rotations = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    static char[][][] keys = new char[16][][];

    static String readplaintext() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter String = ");
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    static void showBits(byte[] arr) {
        int i = 0;
        for (byte b : arr) {
            String s = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.println(s);
            if (++i == 8) System.out.println("\n");
        }
    }

    static byte[] getkey() {
        String b = "001101000planTextAfterS0101101101101011010100000011101110110111001000000000100";
        byte[] bval = new BigInteger(b, 2).toByteArray();
        return bval;
    }

    static String[] initialPermutation(String[] plaintext) {
        char[][] arrChar = new char[8][8];
        for (int i = 0; i < 8; ++i) {
            arrChar[i] = plaintext[i].toCharArray();
        }
        char[][] arrCharCopy = new char[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                arrCharCopy[i][j] = arrChar[(IP[i * 8 + j] - 1) / 8][(IP[i * 8 + j] - 1) % 8];
            }
        }
        for (int i = 0; i < 8; ++i) {
            plaintext[i] = String.valueOf(arrCharCopy[i]);
        }
        return plaintext;
    }

    static String[] initialPermutationkey(String[] key) {
        char[][] arrChar = new char[8][8];
        for (int i = 0; i < 8; ++i) {
            arrChar[i] = key[i].toCharArray();
        }
        char[][] arrCharCopy = new char[8][7];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 7; ++j) {
                arrCharCopy[i][j] = arrChar[(PC1[i * 7 + j] - 1) / 8][(PC1[i * 7 + j] - 1) % 8];
            }
        }
        key = new String[8];
        for (int i = 0; i < 8; ++i) {
            key[i] = String.valueOf(arrCharCopy[i]);
        }
        return key;
    }
    public static int countOccurrences(String haystack, char needle)
    {
        int count = 0;
        for (int i=0; i < haystack.length(); i++)
        {
            if (haystack.charAt(i) == needle)
            {
                count++;
            }
        }
        return count;
    }
    static String[] convertBytestoStringText(byte[] arr) {
        String[] arrStr = new String[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            String s = String.format("%8s", Integer.toBinaryString(arr[i] & 0xFF)).replace(' ', '0');
            arrStr[i] = s;
        }
        return arrStr;
    }
    static String[] convertBytestoStringKey(byte[] arr) {
        StringBuilder arrStr = new StringBuilder();
        for (int i = 0; i < 7; ++i) {
            String s = String.format("%8s", Integer.toBinaryString(arr[i] & 0xFF)).replace(' ', '0');
            arrStr.append(s);
            arrStr.insert(7*(i+1),countOccurrences(s.substring(0,7),'1') % 2 == 0 ? '1' : '0');
        }
        arrStr.append(countOccurrences(arrStr.substring(56,63),'1') % 2 == 0 ? '1' : '0');
        String[] str = new String[8];
        for (int i = 0; i < 8; ++i) {
            str[i] = arrStr.substring(i*8,i*8+8);
        }
        return str;
    }
    public static void show(String[] arr) {
        int i = 0;
        for (String s : arr) {
            System.out.println(s);
            if (++i == 8) System.out.println("\n");
        }
        System.out.println();
    }

    static public void leftPermutationkey(int round, char[][] nums) {

        for (int m = 0; m < rotations[round]; ++m) {
            char temp[][] = new char[nums.length][];
            for (int i = 0; i < nums.length; ++i) {
                temp[i] = nums[i].clone();
            }

            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums[i].length; ++j) {
                    if (i == nums.length - 1 && j == nums[i].length - 1) {
                        nums[i][j] = temp[0][0];
                    } else if (j != nums[i].length - 1) {
                        nums[i][j] = temp[i][j + 1];
                    } else
                        nums[i][j] = temp[i + 1][0];
                }
            }
        }
    }
    static char[][] PC2Permutation(char[][] key) {
        char[][] arrChar = new char[key.length][key[0].length];
        for (int i = 0; i < key.length; ++i) {
            arrChar[i] = key[i].clone();
        }
        char[][] arrCharCopy = new char[key.length][6];
        for (int i = 0; i < key.length; ++i) {
            for (int j = 0; j < 6; ++j) {
                arrCharCopy[i][j] = arrChar[(PC2[i * 6 + j] - 1) / 7][(PC2[i * 6 + j] - 1) % 7];
            }
        }
        return arrCharCopy;
    }
    public static void showCharArr(char[][] arr) {
        String[] temp = new String[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            temp[i] = String.valueOf(arr[i]);
        }
        show(temp);
    }
    public static char[][] connectMat(char[][] m1,char[][] m2) {
        char[][] arr = new char[m1.length + m2.length][m1[0].length + m2[0].length];
        for(int i = 0, j = 0; i < m1.length + m2.length; ++i) {
            if(i < m1.length)
            arr[i] = m1[i].clone();
            else
                arr[i] = m2[j++].clone();
        }
        return  arr;
    }
    static char[][] EBitSelectionTable(char[][] arr){
        char[][] arrChar = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; ++i) {
            arrChar[i] = arr[i].clone();
        }
        char[][] arrCharCopy = new char[8][6];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 6; ++j) {
                //int tempj = (E[i * 6 + j] - 1);
                //while (tempj / 8 != 0) tempj = tempj /  8;
                arrCharCopy[i][j] = arrChar[(E[i * 6 + j] - 1) / 8][(E[i*6 + j] - 1) % 8];
            }
        }
        return arrCharCopy;
    }
    static void xorText2Array(char[][] text, char[][] text2)
    {
        for(int i = 0; i < text.length; ++i)
        {
            for(int j = 0; j < text[i].length; ++j)
            {
                text[i][j] =  (text[i][j] == text2[i][j]) ? '0' : '1';
            }
        }
    }

    static char[][] SBoxRound(char[][] text)
    {
        char[][] temp = new char[8][];
        for(int  i = 0; i < temp.length; ++i) {
            temp[i] = new char[4];
        }

        int row,col;
        for (int i = 0; i < text.length; ++i) {
            //for(int j = 0; j < text[0].length; ++j) {
                row = (text[i][0] == '0' ? 0 : 2) + (text[i][text[i].length - 1] == '0' ? 0 : 1);
                col = (text[i][1] == '0' ? 0 : 8) + (text[i][2] == '0' ? 0 : 4) + (text[i][3] == '0' ? 0 : 2) + (text[i][4] == '0' ? 0 : 1);
                int item = S[i][row*16+col];
                String str = Integer.toString(item,2);
                for(int k = 0; k < 4; ++k) {
                    temp[i][k] = '0';
                }
                for(int k = 0, l = 4 - str.length(); k < str.length(); ++k) {
                    temp[i][k+l] = str.charAt(k);
                }
            //}
        }
        return temp;
    }
   static char[][] permutationP(char[][] arr) {
        char[][] arrChar = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; ++i) {
            arrChar[i] = arr[i].clone();
        }
        char[][] arrCharCopy = new char[8][4];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 4; ++j) {
                //int tempj = (E[i * 6 + j] - 1);
                //while (tempj / 8 != 0) tempj = tempj /  8;
                arrCharCopy[i][j] = arrChar[(P[i * 4 + j] - 1) / 4][(P[i*4 + j] - 1) % 4];
            }
        }
        return arrCharCopy;
    }
    static char[][] permutationAfterRound(char[][] arr) {
        char[][] temp = new char[arr[0].length][arr.length];
        // 00 - 70 / 01 - 60 / 02 -50 / 10 - 71
        for (int i = 0; i < arr[0].length; ++i) {
            for(int j = 0; j < arr.length ; ++j) {
                temp[i][j] = arr[arr.length- 1 - j][i];
            }
        }
        return temp;
    }
    static String[] FinalPermutation(String[] plaintext) {
        char[][] arrChar = new char[8][8];
        for (int i = 0; i < 8; ++i) {
            arrChar[i] = plaintext[i].toCharArray();
        }
        char[][] arrCharCopy = new char[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                arrCharCopy[i][j] = arrChar[(FP[i * 8 + j] - 1) / 8][(FP[i * 8 + j] - 1) % 8];
            }
        }
        for (int i = 0; i < 8; ++i) {
            plaintext[i] = String.valueOf(arrCharCopy[i]);
        }
        return plaintext;
    }


    static void keyGen(String key) {
        String[] keyStr = convertBytestoStringKey(key.getBytes());

        keyStr = initialPermutationkey(keyStr);

        for (int g = 0; g < 16; ++g) {
            char[][] keyChararrC = new char[keyStr.length / 2][7];
            char[][] keyChararrD = new char[keyStr.length / 2][7];
            for (int i = 0, j = -1; i < keyStr.length; ++i) {
                if (i < keyStr.length / 2) {
                    keyChararrC[i] = keyStr[i].toCharArray();
                } else {
                    keyChararrD[++j] = keyStr[i].toCharArray();
                }
            }
            for(int i = 0; i < g; ++i) {
                leftPermutationkey(g, keyChararrC);
                leftPermutationkey(g, keyChararrD);
            }

            char[][] keyTemp = connectMat(keyChararrC, keyChararrD);

            //showCharArr(keyTemp);

            char[][] keyTemp2 = PC2Permutation(keyTemp);
            keys[g] = keyTemp2.clone();
        }
    }


    static String decode(String[] plaintextStr, String key) {

        //String[] plaintextStr = convertBytestoStringText(text.getBytes());
        show(plaintextStr);
        keyGen(key);
        plaintextStr = initialPermutation(plaintextStr);
        for (int g = 0; g < 16; ++g) {

            char[][] keyTemp2 = keys[15 - g];
            //showCharArr(keyTemp2);
            char[][] plainText2half = new char[4][8];
            for (int i = 0; i < 4; ++i) {
                plainText2half[i] = plaintextStr[i + 4].toCharArray().clone();
            }

            //showCharArr(plainText2half);
            char[][] plainTextArr = EBitSelectionTable(plainText2half);
            //showCharArr(plainTextArr);
            xorText2Array(plainTextArr, keyTemp2);

            char[][] planTextAfterS = SBoxRound(plainTextArr);

            //showCharArr(planTextAfterS);
            char[][] planTextAfterP = permutationP(planTextAfterS);

            char[][] plainText1half = new char[4][];
            for (int i = 0; i < 4; ++i) {
                plainText1half[i] = plaintextStr[i].toCharArray().clone();
            }

            char[][] plainTextAfterRound = permutationAfterRound(planTextAfterP);

            xorText2Array(plainText1half, plainTextAfterRound);

            for (int i = 0; i < plaintextStr.length; ++i) {
                if(g != 15)
                    plaintextStr[i] = (i < 4 ) ? String.valueOf(plainText2half[i]) : String.valueOf(plainText1half[i - 4]);
                else
                    plaintextStr[i] = (i < 4 ) ? String.valueOf(plainText1half[i]) : String.valueOf(plainText2half[i - 4]) ;
            }

        }
        plaintextStr =  FinalPermutation(plaintextStr);
        show(plaintextStr);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 8; ++i){
            int charCode = Integer.parseInt(plaintextStr[i], 2);
            String str = new Character((char)charCode).toString();
            s.append(str);
        }


        return s.toString();
    }

   static String[] encrypt(String text, String key){

        String[] plaintextStr = convertBytestoStringText(text.getBytes());
        plaintextStr = initialPermutation(plaintextStr);
        keyGen(key);

        for (int g = 0; g < 16; ++g) {

            char[][] plainText2half = new char[4][8];
            for (int i = 0; i < 4; ++i) {
                plainText2half[i] = plaintextStr[i + 4].toCharArray().clone();
            }

            //showCharArr(plainText2half);
            char[][] plainTextArr = EBitSelectionTable(plainText2half);
            //showCharArr(plainTextArr);
            xorText2Array(plainTextArr, keys[g]);

            char[][] planTextAfterS = SBoxRound(plainTextArr);

            char[][] planTextAfterP = permutationP(planTextAfterS);

            char[][] plainText1half = new char[4][];
            for (int i = 0; i < 4; ++i) {
                plainText1half[i] = plaintextStr[i].toCharArray().clone();
            }

            char[][] plainTextAfterRound = permutationAfterRound(planTextAfterP);

            xorText2Array(plainText1half, plainTextAfterRound);

            for (int i = 0; i < plaintextStr.length; ++i) {
                if(g != 15)
                    plaintextStr[i] = (i < 4 ) ? String.valueOf(plainText2half[i]) : String.valueOf(plainText1half[i - 4]);
                else
                    plaintextStr[i] = (i < 4 ) ? String.valueOf(plainText1half[i]) : String.valueOf(plainText2half[i - 4]) ;
            }
        }
        plaintextStr =  FinalPermutation(plaintextStr);

       show(plaintextStr);
       /*
       StringBuilder s = new StringBuilder();
       for (int i = 0; i < 8; ++i){
           int charCode = Integer.parseInt(plaintextStr[i], 2);
           String str = new Character((char)charCode).toString();
           s.append(str);
       }
       */
       //System.out.println("Result " + s.toString());
        return plaintextStr;
    }

}
