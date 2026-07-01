#!/bin/bash

# ============================================
# HeroAssistant - Build no Termux (JDK 21)
# ============================================

set -e

echo "=========================================="
echo "  HeroAssistant - Build"
echo "=========================================="
echo ""

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# ============================================
# 1. Corrigir repositórios e atualizar
# ============================================
echo -e "${BLUE}[1/4] Corrigindo repositórios...${NC}"
termux-change-repo
pkg update -y && pkg upgrade -y

# ============================================
# 2. Instalar dependências
# ============================================
echo -e "${BLUE}[2/4] Instalando dependências...${NC}"
pkg install -y openjdk-21 wget unzip

# ============================================
# 3. Configurar Android SDK
# ============================================
echo -e "${BLUE}[3/4] Configurando Android SDK...${NC}"

ANDROID_SDK_ROOT="$HOME/android-sdk"
export ANDROID_HOME="$ANDROID_SDK_ROOT"
export PATH="$PATH:$ANDROID_HOME/cmdline-tools/latest/bin"

if [ ! -d "$ANDROID_SDK_ROOT/platforms/android-34" ]; then
    mkdir -p "$ANDROID_SDK_ROOT"
    cd "$ANDROID_SDK_ROOT"
    
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O cmdline-tools.zip
    unzip -q cmdline-tools.zip
    rm cmdline-tools.zip
    
    mkdir -p cmdline-tools/latest
    mv cmdline-tools/* cmdline-tools/latest/ 2>/dev/null
    rmdir cmdline-tools 2>/dev/null || true
    
    yes | sdkmanager --licenses 2>/dev/null || true
    sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
fi

export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))

# ============================================
# 4. Compilar
# ============================================
echo -e "${BLUE}[4/4] Compilando HeroAssistant...${NC}"

cd "$(dirname "$0")"
chmod +x gradlew
./gradlew assembleDebug

echo ""
echo "=========================================="
echo -e "${GREEN}Build concluído!${NC}"
echo "APK: app/build/outputs/apk/debug/app-debug.apk"
echo "=========================================="
