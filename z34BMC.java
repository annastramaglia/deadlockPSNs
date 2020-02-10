package packageGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class z34BMC {
	public int[] N = {1, 2, 3, 4};
	public int[] M = {1, 2, 3};
	public int steps = 5;
	int[] s1 = {0, 2, 2, 2};
	int[] s2 = {3, 0, 3, 3};
	int[] s3 = {4, 4, 0, 4};
	int[] s4 = {1, 1, 0, 0};
	ArrayList<Channel> channels = new ArrayList<Channel>();
	Channel C1 = new Channel(1, 2, 0, s1, 1);
	Channel C2 = new Channel(2, 3, 0, s2, 2);
	Channel C3 = new Channel(3, 4, 0, s3, 3);
	Channel C4 = new Channel(4, 1, 0, s4, 4);


	ArrayList<String> spec = new ArrayList<String>();

	public void writing() {
		channels.add(C1);
		channels.add(C2);
		channels.add(C3);
		channels.add(C4);

		try {
			File statText = new File("C:\\MYZ3\\bin\\z34BMC.smt");
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);    
			Writer w = new BufferedWriter(osw);
			w.write("(declare-fun C (Int Int) Int)\n");
			w.write("(assert\n");
			w.write("(and\n");
			for (int i = 0; i < channels.size(); i++) {
				w.write( "(= (C " + channels.get(i).getName() + " 0) 0)\n");
			}
			for (int j = 1; j <= steps; j++) {
				for (int i = 0; i < channels.size(); i++) {

					//initialization of channels: free
					for(int dest : M) {
						if (dest != channels.get(i).getSource() & channels.get(i).getTarget() == checkRout(channels.get(i), dest)) {
							w.write("(or ");
							w.write("(= (C " + channels.get(i).getName() + " " + j + ") 0) ");
							break;
						}
					}
					// each channel can take the values of M and only if it is in the path of the destination
					for(int dest : M) {
						if (dest != channels.get(i).getSource() & channels.get(i).getTarget() == checkRout(channels.get(i), dest)) {
							w.write("(= (C " + channels.get(i).getName() + " " + j + ") "+ dest + ") ");
						}
					}
					for(int dest : M) {
						if (dest != channels.get(i).getSource() & channels.get(i).getTarget() == checkRout(channels.get(i), dest)) {
							w.write(")\n");
							break;
						}
					}

				}
			}

			// step from 1 to steps
			for (int i = 0; i < steps; i++) {
				//send
				w.write("(or\n");
				for (Channel c : channels) {
					if(checkM(M, c.getSource()) == true){
						for(int dest : M) {
							if (dest != c.getSource() & c.getTarget() == checkRout(c, dest)) {
								w.write("(and (= "); 
								w.write("(C "+ c.getName() + " " + i +") 0) (= (C " + c.getName() + " " + (i+1) + ") "+ dest + ") ");
								for(Channel cc : channels) {
									if (cc.getName() != c.getName()) {
										w.write("(= (C "+ cc.getName() + " " + (i+1) + ") (C "+ cc.getName() + " " + (i) + ")) ");
									}
								}
								w.write(")\n");
							}
						}
					}
				}

				//process
				for (Channel c : channels) {
					Channel ch = new Channel(0, 0, 0, null, 0);
					for (int m : M) {
						if(c.getTarget() != m & c.getSource() != m ) {
							ch = (rout(c, m));
							if(ch.getSource() != m & ch.getName() != 0) { 
								int v = c.getName();
								w.write("(and (= ");
								w.write("(C "+ v + " " + i + ") "+ m + ") (= (C " + ch.getName() + " " + i + ") 0) ");
								w.write("(= (C "+ ch.getName() + " " + (i+1) + ") "+ m + ") (= (C " + v + " " + (i+1) + ") 0) ");
								for(Channel ccc : channels) {
									if (ccc.getName() != ch.getName() & ccc.getName() != v) {
										w.write("(= (C "+ ccc.getName() + " " + (i+1) + ") (C "+ ccc.getName() + " " + (i) + ")) ");
									}
								}
								w.write(")\n");	
							}
						} 
					} 
				}

				//receive
				for (int m : M) {
					for (Channel c : channels) {
						Channel ch = new Channel(0, 0, 0, null, 0);
						ch = (rout(c, m));
						if(ch.getTarget() == m ) {
							int v = ch.getName();
							if(!spec.contains("(C "+ v + " " + i + ") "+ m + ") (= (C " + v + " " + (i+1) + ") 0) ")) {
								w.write("(and (= ");
								w.write("(C "+ v + " " + i + ") "+ m + ") (= (C " + v + " " + (i+1) + ") 0) ");
								spec.add("(C "+ v + " " + i + ") "+ m + ") (= (C " + v + " " + (i+1) + ") 0) ");
								for(Channel ccc : channels) {
									if (ccc.getName() != v) {
										w.write("(= (C "+ ccc.getName() + " " + (i+1) + ") (C "+ ccc.getName() + " " + (i) + ")) ");
									}
								}
								w.write(")\n");	

							}
						}
					}
				}
				w.write(")\n");	
			}

			//last step: deadlock - conditions negated but all the channels should take the values of the previous step
			w.write("(not(or\n");
			//send
			for (Channel c : channels) {
				if(checkM(M, c.getSource()) == true){
					for(int dest : M) {
						if (dest != c.getSource() & c.getTarget() == checkRout(c, dest)) {
							if(!spec.contains("(C "+ c.getName() + " " + steps + ") 0)\n")) {
							w.write("(= "); 
							w.write("(C "+ c.getName() + " " + steps + ") 0)\n");
							spec.add("(C "+ c.getName() + " " + steps + ") 0)\n");
	
							}
						}
					}
				}
			}

			//process
			for (Channel c : channels) {
				Channel ch = new Channel(0, 0, 0, null, 0);
				for (int m : M) {
					if(c.getTarget() != m & c.getSource() != m ) {
						ch = (rout(c, m));
						if(ch.getSource() != m & ch.getName() != 0) { 
							int v = c.getName();
							if(!spec.contains("(C "+ v + " " + steps + ") "+ m + ") (= (C " + ch.getName() + " " + steps + ") 0) ")) {
							w.write("(and (= ");
							w.write("(C "+ v + " " + steps + ") "+ m + ") (= (C " + ch.getName() + " " + steps + ") 0) ");
							spec.add("(C "+ v + " " + steps + ") "+ m + ") (= (C " + ch.getName() + " " + steps + ") 0) ");
							w.write(")\n");	
							}
						}
					} 
				} 
			}

			//receive
			for (int m : M) {
				for (Channel c : channels) {
					Channel ch = new Channel(0, 0, 0, null, 0);
					ch = (rout(c, m));
					if(ch.getTarget() == m ) {
						int v = ch.getName();
						if(!spec.contains("(C "+ v + " " + steps + ") "+ m + ")\n")) {
							w.write("(= ");
							w.write("(C "+ v + " " + steps + ") "+ m + ")\n");
							spec.add("(C "+ v + " " + steps + ") "+ m + ")\n");
						}
					}
				}
			}
			w.write(")\n");
			w.write(")\n");

			// end of last step
			w.write(")\n");
			w.write(")\n");
			w.write("(check-sat)\n");
			w.write("(get-model)\n");

			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}
	}

	// write
	public static void main(String[]args) {
		z34BMC write = new z34BMC();
		write.writing();
	}


	//which path to choose based on vectors above
	public Channel rout(Channel channel, int destination){ 
		Channel c = new Channel(0,0,0, null, 0);

		int x = checkRout(channel, (destination));
		if(channel.getTarget() == x) {
			for (Channel ch : channels) {
				if (ch.getSource() == x & checkRout(ch, (destination)) == ch.getTarget() & checkRout(ch, (destination)) != 0){
					c = ch;
				}
			}
		}

		return c;
	}

	 //check the belonging of an int in an array
	private static boolean checkM(int[] arr, int toCheckValue)
	{ 
		boolean test = false; 
		for (int element : arr) { 
			if (element == toCheckValue) { 
				test = true; 
				break; 
			} 
		}
		return test;
	}

	public class Channel{
		int source; 
		int target; 
		int value;
		int[] s;
		int name;

		public Channel(int source, 
				int target, int value, int[] s, int name) 
		{ 
			this.source = source; 
			this.target = target; 
			this.value = value;
			this.s = s;
			this.name = name;
		} 

		public int getSource() 
		{ 
			return source; 
		} 

		public int getTarget() 
		{ 
			return target; 
		} 

		public int getValue() 
		{ 
			return value;
		} 

		public int[] getS()
		{
			return s;
		}

		public int getName() 
		{ 
			return name;
		} 

		public void setValue(int value) 
		{ 
			this.value = value; 
		} 

	}

	 // find value in the array in a certain index
	private int checkRout(Channel ch, int value) {
		int a = 0;
		ArrayList<Integer> aa = new ArrayList<Integer>();
		for(int k : ch.getS()) {
			aa.add(k);
		}
		a = aa.get(value-1);
		return a;
	}

}