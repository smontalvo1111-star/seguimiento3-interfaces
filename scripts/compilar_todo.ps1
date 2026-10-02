$ErrorActionPreference = 'Stop'
$Root = Split-Path -Parent $PSScriptRoot
Write-Host "Compilando todos los ejemplos, ejercicios y laboratorios..."

$dirs = Get-ChildItem -Path $Root -Recurse -Filter *.java |
    Select-Object -ExpandProperty DirectoryName -Unique |
    Sort-Object

foreach ($dir in $dirs) {
    Write-Host "==> $dir"
    Push-Location $dir
    try {
        Get-ChildItem *.class -ErrorAction SilentlyContinue | Remove-Item -Force
        javac *.java
        if ($LASTEXITCODE -ne 0) { throw "Falló javac en $dir" }
    }
    finally {
        Pop-Location
    }
}

Write-Host "Todo compila correctamente con Java 21."
