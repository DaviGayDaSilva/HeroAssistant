# HeroAssistant

HeroAssistant é um aplicativo Android focado em manutenção e otimização real do dispositivo, com suporte ao **Shizuku** para executar ações avançadas de forma rápida e silenciosa.

## Funcionalidades

- Detecção de apps que consomem muita bateria, armazenamento e dados móveis
- Identificação de arquivos grandes, duplicados e lixo do sistema
- Limpeza de cache rápida com Shizuku ativo
- Desinstalação silenciosa de apps e remoção de bloatware
- Agendamento de limpezas automáticas
- Painel geral do sistema com métricas de uso
- Funcionamento parcial sem Shizuku (modo guiado)

## Requisitos

- Android 8.0 (API 26) ou superior
- [Shizuku](https://shizuku.rikka.app/) instalado para recursos avançados

## Tecnologias

- Kotlin + Jetpack Compose
- Hilt (injeção de dependência)
- Room (banco de dados local)
- WorkManager (agendamento)
- Shizuku API (ações privilegiadas)