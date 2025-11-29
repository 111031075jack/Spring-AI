package com.example.demo.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

	private String ollamaApiUrl = "http://localhost:11434";
	private String defaultModel = "qwen3:0.6b";
	
	@Bean
	 OllamaApi ollamaApi() {
		return OllamaApi.builder()
				.baseUrl(ollamaApiUrl)
				.build();
	}
	
	@Bean
	 OllamaChatModel chatModel(OllamaApi ollamaApi) {
		return OllamaChatModel.builder()
				.ollamaApi(ollamaApi)
				.defaultOptions(
						OllamaChatOptions.builder()
						.model(defaultModel) // 指定模型
						.build()
				)
				.build();
	}
	

	@Bean
	 ChatMemory chatMemory() {
		return MessageWindowChatMemory.builder()
				.maxMessages(100) // 保留最近100則
				.build();
	}
	

	
	
	
}
