package days2022;

import helpers.InputProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7_NoSpaceLeftOnDevice {
	public static int day = 7;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day))); // niet 24933642
	}

	public static int runA(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		collect.remove(0);
		Directory home = new Directory(null, "home");
		home.processLines(collect.iterator());
		home.getSize();
		return home.getSizesMax(100000);
	}

	public static int runB(Stream<String> input) {
		List<String> collect = input.collect(Collectors.toList());
		collect.remove(0);
		Directory home = new Directory(null, "home");
		home.processLines(collect.iterator());

		int spaceRequired = 30_000_000 - (70_000_000 - home.getSize());
		HashMap<String, Integer> canBeDeleted = new HashMap<>();
		home.canBeDeleted(spaceRequired, canBeDeleted);

		return canBeDeleted.values().stream().mapToInt(i -> i).min().getAsInt();
	}

	private static class Directory {
		Map<String, Directory> childDirectories = new HashMap<>();

		Map<String, Integer> fileList = new HashMap<>();

		Directory parent;
		int size;
		String name;

		public Directory(Directory parent, String name) {
			this.name = name;
			this.parent = parent;
		}

		public void canBeDeleted(int minSize, Map<String, Integer> map) {
			if (minSize > size) {
				return;
			}
			map.put(name, size);
			childDirectories.values().stream().forEach(dir -> dir.canBeDeleted(minSize, map));
		}

		public int getSizesMax(int max) {
			int totalMaxSize = childDirectories.values()
					.stream()
					.map(dir -> dir.getSizesMax(max))
					.mapToInt(i -> i)
					.sum();
			if(size > max) {
				return totalMaxSize;
			} else {
				return totalMaxSize + size;
			}
		}

		public int getSize() {
			int sumDirectories = childDirectories.values().stream().map(Directory::getSize).mapToInt(i -> i).sum();
			int fileSizes = fileList.values().stream().mapToInt(i -> i).sum();
			size = sumDirectories + fileSizes;
			return size;
		}

		public void processLines(Iterator<String> iterator) {
			while (iterator.hasNext()) {
				String line = iterator.next();
				String[] s = line.split(" ");
				if (s[0].equals("$")) {
					if (s[1].equals("cd")) {
						if(s[2].equals("..")) {
							return;
						}
						if (!childDirectories.containsKey(s[2])) {
							childDirectories.put(s[2], new Directory(this, s[2]));
						}
						childDirectories.get(s[2]).processLines(iterator);
					}

				} else if (s[0].equals("dir")) {
					if (!childDirectories.containsKey(s[1])) {
						childDirectories.put(s[1], new Directory(this,s[1]));
					}
				} else {
					// files
					if (!fileList.containsKey(s[1])) {
						fileList.put(s[1], Integer.parseInt(s[0]));
					}
				}
			}
		}
	}
}
