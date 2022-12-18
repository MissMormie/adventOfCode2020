package days2022;

import com.google.common.collect.Collections2;
import helpers.InputProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day16_ProboscideaVolcanium {
	public static int day = 16;

	public static int year = 2022;

	public static void main(String[] args) throws IOException {
		System.out.println("answer A: " + runA(InputProvider.getInputFor(year, day)));
		System.out.println("answer B: " + runB(InputProvider.getInputFor(year, day)));
	}

	public static int runA(Stream<String> input) {
		// Parse the data to tunnels
		Map<String, Tunnel> tunnelMap = input.map(Tunnel::new).collect(Collectors.toMap(tunnel -> tunnel.name, Function.identity()));
		// Determine how the tunnels are connected
		tunnelMap.values().forEach(tunnel -> tunnel.getConnectedTunnels(tunnelMap));
		// get the starting tunnel.
		Tunnel tunnelAA = tunnelMap.values().stream().filter(tunnel -> "AA".equals(tunnel.name)).findFirst().get();
		// Get the tunnels that have a positive flowrate
		List<Tunnel> tunnelsWithFlow = tunnelMap.values().stream().filter(tunnel -> tunnel.flowrate > 0).collect(Collectors.toList());
		// determine the distances between the tunnels with flowrate
		tunnelsWithFlow.forEach(tunnel -> tunnel.setDistancesTo(tunnelsWithFlow));

		// get the different permutations possible (excluding AA, since that's where we start)
		// might be able to do something smart here, but lets start by running all permutations.
		Collection<List<Tunnel>> permutations = Collections2.permutations(tunnelsWithFlow);

		return 0;
	}

	public static int runB(Stream<String> input) {
		return 0;
	}

	private static class Tunnel {
		List<Tunnel> connectedTunnels = new ArrayList<>();

		Map<Tunnel, Integer> distanceToTunnel = new HashMap<>();

		int flowrate;

		String name;

		String[] split;

		public Tunnel(String line) {
			split = line.split("Valve | has flow rate=|; tunnel leads to valve |, |; tunnels lead to valves ");
			name = split[1];
			flowrate = Integer.parseInt(split[2]);
		}

		public void getConnectedTunnels(Map<String, Tunnel> tunnelMap) {
			for (int i = 3; i < split.length; i++) {
				connectedTunnels.add(tunnelMap.get(split[i]));
			}
			// You estimate it will take you one minute to follow any tunnel from one valve to another.
			connectedTunnels.forEach(tunnel -> distanceToTunnel.put(tunnel, 1));
		}

		public Integer getDistanceTo(Tunnel tunnel, String tunnelsChecked) {
			// No distance to this tunnel
			if(this == tunnel) {
				return 0;
			}
			// Already know the distance, not calculating again
			if (distanceToTunnel.containsKey(tunnel)) {
				return  distanceToTunnel.get(tunnel) + 1;
			}

			final String tunnelsDone = tunnelsChecked + "," + name;
			// check the connected Tunnels for the shortest distance
			int distance = connectedTunnels.stream()
					// filter out the tunnels already in this path so we don't get a stack overflow.
					// Going around in circles is never the shortest route.
					.filter(connectedTunnel-> !tunnelsChecked.contains(connectedTunnel.name))
					.map(connectedTunnel -> connectedTunnel.getDistanceTo(tunnel, tunnelsDone))
					.mapToInt(i -> i)
					.min()
					.orElseGet(() -> 100);
			// can't reach it in this path anymore, but starting again it should be possible.
			if(distance < 100) {
				distanceToTunnel.put(tunnel, distance);
			}
			return distance + 1;
		}

		public void setDistancesTo(List<Tunnel> tunnelsWithFlow) {
			tunnelsWithFlow
					.forEach(tunnel -> this.getDistanceTo(tunnel, ""));
		}


	}
}
