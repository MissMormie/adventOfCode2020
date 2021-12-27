package days2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day16_PacketDecoderTest {

	@Test
	void runA() {
		String input;
		int answer;


//		input = "620080001611562C8802118E34";
//		answer = 12;
//		assertEquals(answer, Day16_PacketDecoder.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//
//		input = "8A004A801A8002F478";
//		answer = 16;
//		assertEquals(answer, Day16_PacketDecoder.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//
//		input = "C0015000016115A2E0802F182340";
//		answer = 23;
//		assertEquals(answer, Day16_PacketDecoder.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "A0016C880162017C3686B18A3D4780";
		answer = 31;
		assertEquals(answer, Day16_PacketDecoder.runA(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

	}


	@Test
	void runB() {
		String input;
		int answer;


		input = "C200B40A82";
		answer = 3;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "04005AC33890";
		answer = 54;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "880086C3E88112";
		answer = 7;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "CE00C43D881120";
		answer = 9;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "D8005AC2A8F0";
		answer = 1;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "F600BC2D8F";
		answer = 0;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);


		input = "9C005AC2F8F0";
		answer = 0;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);

		input = "9C0141080250320F1802104A08";
		answer = 1;
		assertEquals(answer, Day16_PacketDecoder.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);


	}
}