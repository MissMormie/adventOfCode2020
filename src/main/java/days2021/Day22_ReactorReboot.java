package days2021;

import helpers.Coordinate3D;
import helpers.InputProvider;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Day22_ReactorReboot {
	public static int day = 22;
	public static int year = 2021;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		Set<String> coords = new HashSet();
		input.forEach(line -> {
			Instruction instruction = new Instruction(line);
			if(instruction.applicableForPartA()) {
				for(int x = instruction.minX; x <= instruction.maxX; x++) {
					for(int y = instruction.minY; y <= instruction.maxY; y++) {
						for(int z = instruction.minZ; z <= instruction.maxZ; z++) {
							if(instruction.on) {
								coords.add(Coordinate3D.makeCoordString(x,y,z));
							} else {
								coords.remove(Coordinate3D.makeCoordString(x,y,z));
							}
						}

					}

				}
			}
		});

		return coords.size();
	}

	public static class Instruction {
		boolean on;
		int minX;
		int maxX;
		int minY;
		int maxY;
		int minZ;
		int maxZ;
		Instruction(String line) {
			String[] split = line.split(" |,|\\.\\.|x=|y=|z=");
			on = split[0].equals("on");
			minX = Integer.parseInt(split[2]);
			maxX = Integer.parseInt(split[3]);
			minY = Integer.parseInt(split[5]);
			maxY = Integer.parseInt(split[6]);
			minZ = Integer.parseInt(split[8]);
			maxZ = Integer.parseInt(split[9]);
		}

		boolean applicableForPartA() {
			return minX <= 50 && maxX >= -50
					&& minY <= 50 && maxY >= -50
					&& minZ <= 50 && maxZ >= -50;
		}
	}


	public static int runB(Stream<String> input) {
		return 0;
	}
}
