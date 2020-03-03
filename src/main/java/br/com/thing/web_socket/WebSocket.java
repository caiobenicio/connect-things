package br.com.thing.web_socket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.thing.mqtt.Publisher;

@ServerEndpoint(value = "/ws/sensor")
public class WebSocket {

	private static final Set<Session> sessoes = Collections.synchronizedSet(new HashSet<Session>());

	private static final Publisher publisher = new Publisher();

	@OnOpen
	public void sessaoAberta(Session sessao) {
		System.out.println("sessao aberta");
		sessoes.add(sessao);
	}

	@OnClose
	public void sessaoFechada(Session sessao) {
		System.out.println("sessao fechada");
		sessoes.remove(sessao);
	}

	@OnMessage
	public void receberMensagem(String mensagem) {
		System.out.println("mensagem recebida: " + mensagem);
		//publisher.publishOnTopic(mensagem, mensagem);
	}

	public static void enviarMensagemClientes(String mensagem) {
		for (Session session : sessoes) {
			try {
				session.getBasicRemote().sendText(mensagem);
			} catch (Exception e) {
				System.err.println("Erro ao enviar mensagem para o cliente. " + e.getMessage());
			}
		}
	}
}
