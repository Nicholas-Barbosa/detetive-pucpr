package com.nicholas.pucpr.detetive;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Principal {

	private final Map<Integer, String> pistas = Map.of(1,
			"Bruno e Lucas foram ate o bloco azul porém nāo compraram nada", 2,
			"Lucas foi visto em uma discussao no dia anterior", 3, "Bruno foi até a puc mas nāo foi visto em sala", 4,
			"a aula do dia era com a prof rafaela no 2 andar", 5, "a pessoa com quem Lucas discutiu foi a prof rafaela",
			6, "a arma com que Lucas foi morto era uma caneta azul", 7,
			"Bruno e lucas brigaram pois os dois queriam sair com Juli", 8, "Julia aceitou sair com Lucas", 9,
			"A caneta era de Bruno", 10, "A professora vinha observando Lucas há 2 meses ");

	private final Map<Integer, String> opcoesRespostas = new HashMap<>(
			Map.of(1, "Lucas se suícidou", 2, "Lucas não morreu. Forjou seu assassinato;", 3, "Bruno o matou", 4,
					"Professora Rafaela foi quem cometou o crime", 5,
					"Lucas morreu envenenado, pois seu café de cada dia estava com veneno de rato", 6,
					"Lucas estava devendo dinheiro, então desovaram o corpo.", 7, "Julia matou Lucas", 8,
					"Pai de Julia, inconformado com o relacionamento de sua filha com Lucas, cometeu assassinato", 9,
					"Coordenador de seu curso", 10, "Ronaldinho"));

	private final Random random = new Random();

	private int correctOptionPosition;
	private final List<Integer> keyPistasComProfessora = List.of(4, 5, 10);
	private boolean pistaComProfRafaela = false;

	public static void main(String[] args) {
		int tentativas = 0;
		final Scanner scanner = new Scanner(System.in);
		Principal obj = new Principal();

		System.out.println("--Você é o detetive da história. Resolva o caso!---");
		System.out.println(
				"Bruno e Lucas sāo muito amigos e sempre vāo juntos até a puc, eles descem no portāo 2 e atravessam a universidade até o bloco 9, onde no meio do caminho sempre param para tomar um café no bloco azul.\nCerto dia Lucas é encontrado morto no 2 andar do bloco 9.\nQuem asassinou Lucas?! ");
		System.out.println("---Jogo iniciado " + dateNow() + "---");
		System.out.println("---Primeira pista: " + obj.getRandomPista() + "---");
		try (scanner) {
			while (tentativas <= 10) {
				tentativas++;
				System.out.println("Nova pista ou novas opções de respotas?1-Nova pista, 2-novas opções de resposta");
				int fopcao = scanner.nextInt();
				switch (fopcao) {
				case 1:
					System.out.println("Pista: " + obj.getRandomPista());
					break;
				}
				System.out.println("---Opções de respostas---");
				String opcoesRandomicas[] = obj.getRandomOptions();
				for (int i = 0; i < 3; i++) {
					System.out.println(i + "-" + opcoesRandomicas[i]);
				}
				int opcao = scanner.nextInt();
				if (obj.correctOptionPosition != -1) {
					if (opcao == obj.correctOptionPosition) {
						System.out.println("Você desvendou o crime. Você usou " + tentativas + " tentativas de 10!!!");

						break;
					}
				}

				System.out.println("Você não conseguiu desvendar o crime nesta rodada!");
			}
			System.out.println("---Jogo encerrado " + dateNow() + "---");
		}
	}

	public String getRandomPista() {
		int key = this.random.nextInt(11);
		pistaComProfRafaela = keyPistasComProfessora.stream().anyMatch(kp -> kp == key);
		return this.pistas.getOrDefault(key, "Sem pistas para você por enquanto!");
	}

	public String[] getRandomOptions() {
		int keys[] = { this.random.nextInt(11), this.pistaComProfRafaela ? 4 : this.random.nextInt(11),
				this.random.nextInt(11) };
		Arrays.sort(keys);
		int correctKey = Arrays.binarySearch(keys, 4);
		correctOptionPosition = correctKey >= 0 ? correctKey : -1;
		return new String[] {
				this.opcoesRespostas.getOrDefault(keys[0], "Quem sou e quem é você?Nessa história não sei dizer...!!!"),
				this.opcoesRespostas.getOrDefault(keys[1], "Quem sou e quem é você?Nessa história não sei dizer...!!!"),
				this.opcoesRespostas.getOrDefault(keys[2],
						"Quem sou e quem é você?Nessa história não sei dizer...!!!") };
	}

	public static String dateNow() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss"));
	}
}
