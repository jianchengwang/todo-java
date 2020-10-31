package todo.core.nio.example;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesExample {

    public static void main(String[] args) {

        try {

            // judge exist
            Path path = Paths.get(FilesExample.class.getResource("/data.txt").getPath());
            boolean pathExists =
                    Files.exists(path,
                            new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
            System.out.println(pathExists);

            // create dir
            Path path1 = Paths.get(FilesExample.class.getResource("..").getPath() + "/newDir");
            Path newDir = Files.createDirectory(path1);

            // copy file
            Path sourcePath      = Paths.get(FilesExample.class.getResource("/data.txt").getPath());
            Path destinationPath = Paths.get(FilesExample.class.getResource(".").getPath() + "/data1.txt");
            Path destinationPath1 = Paths.get(FilesExample.class.getResource(".").getPath()+ "/data2.txt");

            // 如果文件已经存在则覆盖
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            // move file
            Files.move(destinationPath, destinationPath1,
                    StandardCopyOption.REPLACE_EXISTING);

            // delete file
            Files.delete(destinationPath1);

            // walk file tree
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("pre visit dir:" + dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visit file: " + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("visit file failed: " + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    System.out.println("post visit directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
