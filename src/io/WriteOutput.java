package io;

import java.util.List;
import com.google.gson.Gson;
import Broadcast.Update;

public class WriteOutput {
	public static void writeOutput(List<Update> outputs) {
		Gson g = new Gson();
		for (Update output : outputs) {
			System.out.println(g.toJson(output));
		}
	}
}
