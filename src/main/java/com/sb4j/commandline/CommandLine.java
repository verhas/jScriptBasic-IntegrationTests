package com.sb4j.commandline;

import com.sb4j.extensions.Range;
import com.sb4j.hooks.LoggingHook;
import com.scriptbasic.api.ScriptBasic;
import com.scriptbasic.api.ScriptBasicException;
import com.scriptbasic.api.Subroutine;
import com.scriptbasic.spi.Interpreter;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Optional;

public class CommandLine {
  static PrintWriter output;

  public static void main(final String[] args) throws Exception {
    if (args.length != 1) {
      System.err
          .println("usage: java [-Dsb4j.extensionclasses=comma separated list of extension classes] "
              + "[-cp classpath for extensions] -jar jscriptbasic-x.y.z basicprogram.sb");
      System.exit(1);
    }
    final String basicProgramFileName = args[0];

    output = new PrintWriter(System.out);
    final PrintWriter error = new PrintWriter(System.err);
    try {
      final InputStreamReader input = new InputStreamReader(System.in);
      final ScriptBasic sb = ScriptBasic.getEngine();
      sb.setInput(input);
      sb.setOutput(output);
      sb.setErrorOutput(error);
      sb.registerExtension(ScriptBasic.fileHandlingFunctionsClass);
      sb.registerFunction("myPrint", CommandLine.class, "myPrint", String.class);
      sb.registerExtension(Range.class);
      sb.registerHook(new LoggingHook());
      registerSystemPropertyDefinedClasses(sb);
      sb.load(new FileReader(basicProgramFileName));
      Subroutine sub = sb.getSubroutine("pleaseCallMe");
      sub.call();
      //sb.execute();
    } catch (final Exception exception) {
      final Throwable cause = Optional.ofNullable(exception.getCause()).orElse(exception);
      if (cause.getMessage() != null) {
        System.err.println("ERROR: " + cause.getMessage());
        exception.printStackTrace(error);
      } else {
        throw exception;
      }
    } finally {
      output.flush();
      error.flush();
    }
  }

  public static void myPrint(Interpreter interpreter, String s) throws ScriptBasicException {
    ((PrintWriter) interpreter.getOutput()).println(interpreter.getVariable(s));
  }

  private static void registerSystemPropertyDefinedClasses(final ScriptBasic ctx) throws ClassNotFoundException, ScriptBasicException {
    final String classes = System.getProperty("sb4j.extensionclasses");
    if (classes != null && classes.length() > 0) {
      final String[] classNames = classes.split(",");
      for (final String className : classNames) {
        final Class<?> klass = Class.forName(className);
        ctx.registerExtension(klass);
      }
    }
  }
}
