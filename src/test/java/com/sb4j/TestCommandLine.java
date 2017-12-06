package com.sb4j;

import com.sb4j.commandline.CommandLine;
import org.junit.Test;

public class TestCommandLine {

  @Test
  public void testSimpleCall() throws Exception {
    CommandLine.main(new String[]{"src/test/resources/hello.bas"});
  }

}
