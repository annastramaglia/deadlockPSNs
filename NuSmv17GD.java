package packageGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;



public class NuSmv17GD {
	public int nodes = 17; // #nodes
	public int[] N = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	public int[] M = {5,11,14};
	int size = 0;
	ArrayList<Integer> last = new ArrayList<Integer>();
	int[] s1 = {0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}; // source is in 1 and the target for going everywhere is 2
	int[] s2 = {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
	int[] s3 = {17, 17, 0, 4, 4, 4, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17};
	int[] s4 = {6, 6, 6, 0, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
	int[] s5 = {6, 6, 6, 6, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
	int[] s6 = {7, 7, 7, 7, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
	int[] s7 = {17, 17, 17, 17, 17, 17, 0, 8, 8, 8, 17, 17, 17, 17, 17, 17, 17};
	int[] s8 = {10, 10, 10, 10, 10, 10, 10, 0, 9, 10, 10, 10, 10, 10, 10, 10, 10};
	int[] s9 = {10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 10, 10, 10, 10, 10, 10, 10};
	int[] s10 = {11, 11, 11, 11, 11, 11, 11, 11, 11, 0, 11, 11, 11, 11, 11, 11, 11};
	int[] s11 = {17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 0, 12, 12, 12, 17, 17, 17};
	int[] s12 = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 0, 13, 13, 13, 13, 13};
	int[] s13 = {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 0, 14, 14, 14, 14};
	int[] s14 = {15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 15, 15, 15};
	int[] s15 = {16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 0, 16, 17};
	int[] s16 = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2};
	int[] s17 = {15, 15, 3, 3, 3, 3, 7, 7, 7, 7, 11, 11, 11, 11, 15, 15, 0};
	ArrayList<Channel> channels = new ArrayList<Channel>();
	Channel C1 = new Channel(1, 2, 0, s1, "C1");
	Channel C2 = new Channel(2, 3, 0, s2, "C2");
	Channel C3 = new Channel(3, 4, 0, s3, "C3");
	Channel C4 = new Channel(3, 17, 0, s3, "C4");
	Channel C5 = new Channel(4, 5, 0, s4, "C5");
	Channel C6 = new Channel(4, 6, 0, s4, "C6");
	Channel C7 = new Channel(5, 6, 0, s5, "C7");
	Channel C8 = new Channel(6, 7, 0, s6, "C8");
	Channel C9 = new Channel(7, 8, 0, s7, "C9");
	Channel C10 = new Channel(7, 17, 0, s7, "C10");
	Channel C11 = new Channel(8, 9, 0, s8, "C11");
	Channel C12 = new Channel(8, 10, 0, s8, "C12");
	Channel C13 = new Channel(9, 10, 0, s9, "C13");
	Channel C14 = new Channel(10, 11, 0, s10, "C14");
	Channel C15 = new Channel(11, 12, 0, s11, "C15");
	Channel C16 = new Channel(11, 17, 0, s11, "C16");
	Channel C17 = new Channel(12, 13, 0, s12, "C17");
	Channel C18 = new Channel(13, 14, 0, s13, "C18");
	Channel C19 = new Channel(14, 15, 0, s14, "C19");
	Channel C20 = new Channel(15, 16, 0, s15, "C20");
	Channel C21 = new Channel(15, 17, 0, s15, "C21");
	Channel C22 = new Channel(16, 1, 0, s16, "C22");
	Channel C23 = new Channel(16, 2, 0, s16, "C23");
	Channel C24 = new Channel(17, 3, 0, s17, "C24");
	Channel C25 = new Channel(17, 7, 0, s17, "C25");
	Channel C26 = new Channel(17, 11, 0, s17, "C26");
	Channel C27 = new Channel(17, 15, 0, s17, "C27");

	ArrayList<String> spec = new ArrayList<String>(); // list of conditions
	ArrayList<String> specUnique = new ArrayList<String>(); // list of conditions with no duplicates

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

		try {

			File statText = new File("C:\\Users\\Anna\\Desktop\\FinalProject\\NuSMV-2.6.0-win64\\bin\\NuSmv17GD.txt");
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);    
			Writer w = new BufferedWriter(osw);
			w.write("MODULE main"+ "\n");
			w.write("VAR" + "\n");
			for (int i = 0; i < channels.size(); i++) {
				w.write(channels.get(i).getName() + " : 0.." + nodes + ";\n");
			}
			w.write("INIT\n");
			for (int i = 0; i < (channels.size()-1); i++) {
				w.write(channels.get(i).getName() + " = 0 & ");
			}
			w.write(channels.get(channels.size()-1).getName()+ " = 0 \n");
			w.write("TRANS\n");

			//send
			for (Channel c : channels) {
				if(checkM(M, c.getSource()) == true){
					for(int dest : M) {
						if (dest != c.getSource() & c.getTarget() == checkRout(c, dest)) {
							w.write("case "); 
							w.write(c.getName() + " = " + c.getValue());
							spec.add(c.getName() + " = " + c.getValue());
							w.write(" : next("+ c.getName() +") = "+ dest);
							for(Channel cc : channels) {
								if (cc.getName() != c.getName()) {
									w.write(" & next("+ cc.getName() + ") = "+ cc.getName());
								}
							}
							w.write(";");
							w.write("\n");
							w.write("TRUE : next("+ c.getName() + ") = "+ c.getName());
							for(Channel cc : channels) {
								if (cc.getName() != c.getName()) {
									w.write(" & next("+ cc.getName() + ") = "+ cc.getName());
								}
							}
							w.write("; esac |");
							w.write("\n");
						}
					}
				}
			}
			w.write("\n");

			//process
			for (Channel c : channels) { 
				Channel ch = new Channel(0, 0, 0, null, "");
				for (int m : M) { 
					if(c.getTarget() != m & c.getSource() != m ) {
						ch = (rout(c, m)); 
						if(ch.getSource() != m & ch.getName() != "") { 
							String v = c.getName();
							w.write("case ");
							w.write(v + " = "+ m + " & " + ch.getName() + " = 0");
							spec.add(v + " = "+ m + " & " + ch.getName() + " = 0");
							w.write(" : next("+ v +") = 0 & next(" + ch.getName() + ") = " + m);
							for(Channel ccc : channels) {
								if (ccc.getName() != ch.getName() & ccc.getName() != v) {
									w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
								}
							}
							w.write(";");
							w.write("\n");
							w.write("TRUE : next("+ v + ") = "+ v);
							for(Channel ccc : channels) {
								if (ccc.getName() != v) {
									w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
								}
							}
							w.write("; esac |");
							w.write("\n");
						}
					}
				} 
			} 

			w.write("\n");

			// receive
			for (int m : M) {
				for (Channel c : channels) {
					Channel ch = new Channel(0, 0, 0, null, "");
					ch = (rout(c, m));
					if(ch.getTarget() == m & m != M[M.length-1]) {
						String v = ch.getName();
						if(!spec.contains(v + " = "+ m)) {
							w.write("case ");
							w.write(v + " = "+ m);
							spec.add(v + " = "+ m);
							w.write(" : next("+ v +") = 0");
							for(Channel ccc : channels) {
								if (ccc.getName() != ch.getName()) {
									w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
								}
							}
							w.write(";");
							w.write("\n");
							w.write("TRUE : next("+ v + ") = "+ v);
							for(Channel ccc : channels) {
								if (ccc.getName() != ch.getName()) {
									w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
								}
							}

							w.write("; esac |");
							w.write("\n");
						}
					}
				}
			}

			for (int m : M) {
				ArrayList<Channel> list = new ArrayList<Channel>();
				for (Channel ch : channels) {
					Channel channel = new Channel(0, 0, 0, null, "");
					channel = (rout(ch, m));
					if(channel.getTarget() == m & m == M[M.length-1]) {	
						list.add(channel);
					}
				}
				for(Channel c : list) {
					String v = c.getName();
					if(list.size() > 1) {
						if(c.equals(list.get(list.size()-1))) {
							if(!spec.contains(v + " = "+ m)) {
								w.write("case ");
								w.write(v + " = "+ m);
								spec.add(v + " = "+ m);
								w.write(" : next("+ v +") = 0");
								for(Channel ccc : channels) {
									if (ccc.getName() != c.getName()) {
										w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
									}
								}
								w.write(";");
								w.write("\n");
								w.write("TRUE : next("+ v + ") = "+ v);
								for(Channel ccc : channels) {
									if (ccc.getName() != c.getName()) {
										w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
									}
								}
								w.write("; esac ;");
								w.write("\n");

							}
						}
						else {
							if(!spec.contains(v + " = "+ m)) {
								w.write("case ");
								w.write(v + " = "+ m);
								spec.add(v + " = "+ m);
								w.write(" : next("+ v +") = 0");
								for(Channel ccc : channels) {
									if (ccc.getName() != c.getName()) {
										w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
									}
								}
								w.write(";");
								w.write("\n");
								w.write("TRUE : next("+ v + ") = "+ v);
								for(Channel ccc : channels) {
									if (ccc.getName() != c.getName()) {
										w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
									}
								}
								w.write("; esac |");
								w.write("\n");
							}

						}
					}else {
						if(!spec.contains(v + " = "+ m)) {
							w.write("case ");
							w.write(v + " = "+ m);
							spec.add(v + " = "+ m);
							w.write(" : next("+ v +") = 0");
							for(Channel ccc : channels) {
								if (ccc.getName() != c.getName()) {
									w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
								}
							}
							w.write(";");
							w.write("\n");
							w.write("TRUE : next("+ v + ") = "+ v);
							for(Channel ccc : channels) {
								if (ccc.getName() != c.getName()) {
									w.write(" & next("+ ccc.getName() + ") = "+ ccc.getName());
								}
							}
							w.write("; esac ;");
							w.write("\n");
						}
					}
				}
			}

			w.write("\n");

			//to obtain a list of unique conditions to put in SPEC 
			for(int i = 0; i <(nodes-2); i++) {
				specUnique = checkList(spec);
			}

			System.out.print(specUnique);

			// negation of the | between all the conditions above
			w.write("CTLSPEC !EF (!(");
			for(int i = 0; i < (specUnique.size()-1); i++) {
				w.write("("+ specUnique.get(i)+ ") | ");
			}
			w.write("("+ specUnique.get(specUnique.size()-1)+ ")");
			w.write("));");




			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}
	}

	// write
	public static void main(String[]args) {
		NuSmv17GD write = new NuSmv17GD();
		write.writing();
	}


	public Channel rout(Channel channel, int destination){
		Channel c = new Channel(0,0,0, null, "");

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
		String name;

		public Channel(int source, 
				int target, int value, int[] s, String name) 
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

		public String getName() 
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


	private ArrayList<String> checkList(ArrayList<String> arr) 
	{ 
		for (int i = 1; i< arr.size(); i++) { 
			if((arr.get(i)).equals(arr.get(i-1))){
				arr.remove(i);
			}

		}
		return arr;
	}


}



