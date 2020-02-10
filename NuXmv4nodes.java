package packageGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;



public class NuXmv4nodes {
	public int nodes = 4; // #nodes
	public int[] N = {1, 2, 3, 4};
	public int[] M = {1, 2, 3};
	int size = 0;
	ArrayList<Integer> last = new ArrayList<Integer>();
	int[] s1 = {0, 2, 2, 2};
	int[] s2 = {3, 0, 3, 3};
	int[] s3 = {4, 4, 0, 4};
	int[] s4 = {1, 1, 0, 0};
	ArrayList<Channel> channels = new ArrayList<Channel>();
	
	Channel C1 = new Channel(1, 2, 0, s1, "C1");
	Channel C2 = new Channel(2, 3, 0, s2, "C2");
	Channel C3 = new Channel(3, 4, 0, s3, "C3");
	Channel C4 = new Channel(4, 1, 0, s4, "C4");
	ArrayList<String> spec = new ArrayList<String>(); // list of conditions
	ArrayList<String> specUnique = new ArrayList<String>(); // list of conditions with no duplicates

	public void writing() {

		channels.add(C1);
		channels.add(C2);
		channels.add(C3);
		channels.add(C4);
	
		try {

			File statText = new File("C:\\Users\\Anna\\Desktop\\FinalProject\\nuXmv-1.1.1-win64\\bin\\NuXmv4nodes.txt");
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
							w.write("TRUE : next("+ c.getName() + ") = " + c.getName() );
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
					if(channel.getTarget() == m & m == M[M.length-1] & !(list.contains(channel))) {	
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
		NuXmv4nodes write = new NuXmv4nodes();
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



