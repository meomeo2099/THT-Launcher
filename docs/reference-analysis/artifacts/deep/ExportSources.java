import jadx.api.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class ExportSources {
  static String sha(byte[] bytes) throws Exception {
    return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bytes));
  }
  public static void main(String[] argv) throws Exception {
    Path apk = Path.of(argv[0]);
    Path out = Path.of(argv[1]);
    Files.createDirectories(out);
    JadxArgs args = new JadxArgs();
    args.setInputFile(apk.toFile());
    args.setThreadsCount(1);
    args.setSkipResources(true);
    args.setDeobfuscationOn(false);
    List<String> inventory = new ArrayList<>();
    inventory.add("raw_class\tdisplay_class\tfile\tbytes\tsha256\tstatus");
    try (JadxDecompiler d = new JadxDecompiler(args)) {
      d.load();
      for (JavaClass c : d.getClasses()) {
        String raw = c.getRawName();
        String relative = raw.replace('.', '/') + ".java";
        Path f = out.resolve(relative).normalize();
        if (!f.startsWith(out)) throw new IllegalArgumentException("unsafe class path");
        try {
          byte[] body = c.getCode().getBytes(StandardCharsets.UTF_8);
          Files.createDirectories(f.getParent());
          Files.write(f, body);
          inventory.add(raw + "\t" + c.getFullName() + "\t" + relative + "\t" + body.length + "\t" + sha(body) + "\tok");
        } catch (Throwable t) {
          inventory.add(raw + "\t" + c.getFullName() + "\t" + relative + "\t0\t\terror:" + t.getClass().getName());
        }
      }
      Files.write(out.resolve("class-inventory.tsv"), inventory, StandardCharsets.UTF_8);
      Files.writeString(out.resolve("export-provenance.txt"),
        "Supporting JADX API export, not REA Evidence.\nAPK SHA256=" + sha(Files.readAllBytes(apk)) +
        "\nJADX=" + JadxDecompiler.getVersion() + "\ntop-level-classes=" + d.getClasses().size() +
        "\nerrors=" + d.getErrorsCount() + "\nwarnings=" + d.getWarnsCount() + "\n");
      System.out.println("Exported " + d.getClasses().size() + " classes; errors=" + d.getErrorsCount() + "; warnings=" + d.getWarnsCount());
    }
  }
}
