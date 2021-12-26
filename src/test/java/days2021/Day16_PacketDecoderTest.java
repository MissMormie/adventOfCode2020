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
//
//
//	@Test
//	void runB() {
//		String input;
//		int answer;
//
//		input = "";
//		answer = 0;
//		assertEquals(answer, Day0.runB(Arrays.stream(input.split("\n"))), "input = " + input + " answer: " + answer);
//	}
}