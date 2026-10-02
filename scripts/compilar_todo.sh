#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"

echo "Compilando todos los ejemplos, ejercicios y laboratorios..."
while IFS= read -r dir; do
  echo "==> $dir"
  (cd "$dir" && rm -f ./*.class && javac ./*.java)
done < <(find "$ROOT" -type f -name '*.java' -printf '%h\n' | sort -u)

echo "Todo compila correctamente con Java 21."
