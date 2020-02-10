# deadlockPSNs
Investigation of Global, Local and Weak Deadlock for examples of Packet Switching Networks using different tools.

PSN: an artificial format mimicking sending, processing and receiving messages in networks as it appears in practice. Composed by a number
of nodes and a number of channels. The main nodes are the only ones that can send and receive messages, the others can only process them.

# nuXmv-NuSmv-z3
file names: tool+numberOfNodes+(typeOfDeadlock||typeOfTechnique)

The set of main nodes can be changed from the .java files, changing the "public int[] M ={...}", producing then a different filename.txt
if the .java produces a code to be run in nuXmv or NuSmv, or a different filename.smt if it is the case of a code to run with z3.

# nuXmv-NuSmv
filename1.java produces filename1.txt

# z3
filename2.java produces filename2.smt

# xMAS, MaDL language
file names: tool+numberOfNodes+(set||numberOfMainNodes)
