PRINT "HELLO, WORLD\n"
Z = range(3,7)
print length(Z),"\n"

printArray Z

sub printArray(X)
for i= 0 to length(X)-1
print i," ",X[i],"\n"
next i
endsub

TEXT = "global variable"

call myMyPrint

sub myMyPrint
  local TEXT
  TEXT= "local variable"
  myPrint "TEXT"
endsub

sub pleaseCallMe
  PRINT "thanks for calling"
endsub