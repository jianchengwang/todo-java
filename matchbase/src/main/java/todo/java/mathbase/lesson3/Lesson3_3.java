package todo.java.mathbase.lesson3;

/**
 * @author jianchengwang
 * @date 2022/10/10
 */
public class Lesson3_3 {
    /**
     * @Description: 查找某个单词是否在字典里出现
     * @param dictionary-排序后的字典, wordToFind-待查的单词
     * @return boolean-是否发现待查的单词
     */
    public static boolean search(String[] dictionary, String wordToFind) {

        if (dictionary == null) {
            return false;
        }

        if (dictionary.length == 0) {
            return false;
        }

        int left = 0, right = dictionary.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (dictionary[middle].equals(wordToFind)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(wordToFind) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return false;

    }

    /**
     * @Description: 查找某个单词是否在字典里出现
     * @param dictionary-排序后的字典, wordToFind-待查的单词
     * @return boolean-是否发现待查的单词
     */
    public static boolean search2(String[] dictionary, String wordToFind) {

        if (dictionary == null) {
            return false;
        }

        if (dictionary.length == 0) {
            return false;
        }

        int left = 0, right = dictionary.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (dictionary[middle].equals(wordToFind)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(wordToFind) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return false;

    }
}
