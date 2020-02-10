package packageGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;


public class z321BMC {
	public int[] N = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
	public int[] M = {1,9,15,19};
	public int steps = 30;
	ArrayList<Integer> last = new ArrayList<Integer>();
	int[] s1 = {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}; // source is in 1 and the target for going everywhere is 2
	int[] s2 = {3, 0, 3, 3, 3, 3, 3, 8, 8, 8, 3, 12, 3, 3, 3, 3, 3, 3, 3, 3, 3};;
	int[] s3 = {7, 7, 0, 4, 7, 7, 7, 7, 12, 12, 11, 12, 7, 7, 7, 7, 7, 21, 21, 21, 21};
	int[] s4 = {3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 20, 20, 20, 21};
	int[] s5 = {7, 7, 4, 4, 0, 7, 7, 7, 4, 4, 4, 4, 7, 16, 16, 16, 17, 4, 4, 4, 4};
	int[] s6 = {1, 1, 1, 5, 5, 0, 7, 1, 1, 1, 7, 1, 13, 5, 5, 5, 17, 5, 5, 5, 5};
	int[] s7 = {6, 2, 3, 6, 6, 6, 0, 2, 2, 2, 3, 2, 6, 6, 6, 6, 6, 3, 3, 3, 3};
	int[] s8 = {9, 9, 9, 9, 9, 9, 9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
	int[] s9 = {10, 10, 10, 10, 10, 10, 10, 10, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
	int[] s10 = {11, 11, 11, 11, 11, 11, 11, 11, 11, 0, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11};
	int[] s11 = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
	int[] s12 = {2, 2, 2, 2, 2, 2, 2, 2, 9, 9, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2};
	int[] s13 = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6};
	int[] s14 = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 0, 13, 13, 13, 13, 13, 13, 13};
	int[] s15 = {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 14, 14, 14, 14, 14, 14};
	int[] s16 = {15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 15, 15, 15, 15, 15};
	int[] s17 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5};
	int[] s18 = {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 0, 11, 11, 11};
	int[] s19 = {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 0, 18, 18};
	int[] s20 = {19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0, 19};
	int[] s21 = {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0};
	ArrayList<Channel> channels = new ArrayList<Channel>();
	Channel C1 = new Channel(1, 2, 0, s1, 1);
	Channel C2 = new Channel(2, 3, 0, s2, 2);
	Channel C3 = new Channel(2, 8, 0, s2, 3);
	Channel C4 = new Channel(2, 12, 0, s2, 4);
	Channel C5 = new Channel(3, 7, 0, s3, 5);
	Channel C6 = new Channel(3, 11, 0, s3, 6);
	Channel C7 = new Channel(3, 12, 0, s3, 7);
	Channel C8 = new Channel(3, 21, 0, s3, 8);
	Channel C9 = new Channel(4, 3, 0, s4, 9);
	Channel C10 = new Channel(4, 20, 0, s4, 10);
	Channel C11 = new Channel(4, 21, 0, s4, 11);
	Channel C12 = new Channel(5, 4, 0, s5, 12);
	Channel C13 = new Channel(5, 7, 0, s5, 13);
	Channel C14 = new Channel(5, 16, 0, s5, 14);
	Channel C15 = new Channel(5, 17, 0, s5, 15);
	Channel C16 = new Channel(6, 1, 0, s6, 16);
	Channel C17 = new Channel(6, 5, 0, s6, 17);
	Channel C18 = new Channel(6, 7, 0, s6, 18);
	Channel C19 = new Channel(6, 13, 0, s6, 19);
	Channel C20 = new Channel(6, 17, 0, s6, 20);
	Channel C21 = new Channel(7, 2, 0, s7, 21);
	Channel C22 = new Channel(7, 3, 0, s7, 22);
	Channel C23 = new Channel(7, 6, 0, s7, 23);
	Channel C24 = new Channel(8, 9, 0, s8, 24);
	Channel C25 = new Channel(9, 10, 0, s9, 25);
	Channel C26 = new Channel(10, 11, 0, s10, 26);
	Channel C27 = new Channel(11, 3, 0, s11, 27);
	Channel C28 = new Channel(12, 2, 0, s12, 28);
	Channel C29 = new Channel(12, 9, 0, s12, 29);
	Channel C30 = new Channel(13, 6, 0, s13, 30);
	Channel C31 = new Channel(14, 13, 0, s14, 31);
	Channel C32 = new Channel(15, 14, 0, s15, 32);
	Channel C33 = new Channel(16, 15, 0, s16, 33);
	Channel C34 = new Channel(17, 5, 0, s17, 34);
	Channel C35 = new Channel(18, 11, 0, s18, 35);
	Channel C36 = new Channel(19, 18, 0, s19, 36);
	Channel C37 = new Channel(20, 19, 0, s20, 37);
	Channel C38 = new Channel(21, 4, 0, s21, 38);
	ArrayList<String> spec = new ArrayList<String>(); // list of conditions

	public void writing() {
		channels.add(C1);
		channels.add(C2);
		channels.add(C3);
		channels.add(C4);
		channels.add(C5);
		channels.add(C6);
		channels.add(C7);
		channels.add(C8);
		channels.add(C9);
		channels.add(C10);
		channels.add(C11);
		channels.add(C12);
		channels.add(C13);
		channels.add(C14);
		channels.add(C15);
		channels.add(C16);
		channels.add(C17);
		channels.add(C18);
		channels.add(C19);
		channels.add(C20);
		channels.add(C21);
		channels.add(C22);
		channels.add(C23);
		channels.add(C24);
		channels.add(C25);
		channels.add(C26);
		channels.add(C27);
		channels.add(C28);
		channels.add(C29);
		channels.add(C30);
		channels.add(C31);
		channels.add(C32);
		channels.add(C33);
		channels.add(C34);
		channels.add(C35);
		channels.add(C36);
		channels.add(C37);
		channels.add(C38);
		try {
			File statText = new File("C:\\MYZ3\\bin\\z321BMC.smt");
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

			// step from 1 to 22
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
		z321BMC write = new z321BMC();
		write.writing();
	}


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

	private static boolean checkM(int[] arr, int toCheckValue) //check the belonging of an int in an array
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

	private int checkRout(Channel ch, int value) { // find value in the array in a certain index
		int a = 0;
		ArrayList<Integer> aa = new ArrayList<Integer>();
		for(int k : ch.getS()) {
			aa.add(k);
		}
		a = aa.get(value-1);
		return a;
	}

}