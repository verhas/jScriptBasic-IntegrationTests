PRINT reverse("\nDLROW ,OLLEH")
Z = range(inc(2),7)
print "printing the length(Z) that has to be 5: ",length(Z),"\n"

PRINT "Printing the content of the array, has to be 3 to 7\n"
printArray Z

sub printArray(X)
  PRINT "("
  for i= 0 to length(X)-1
    if i <> 0 then
      print ", "
    endif
    print X[i]
  next i
  PRINT ")\n"
endsub

TEXT = "global variable"

call myMyPrint

sub myMyPrint
  local TEXT
  TEXT= "local variable"
  PRINT "Printing the context of the valriable TEXT should be \"local variable\"\n"
  myPrint "TEXT"
endsub

sub pleaseCallMe
  PRINT "thanks for calling\n"
endsub