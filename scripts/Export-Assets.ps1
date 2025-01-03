Remove-Item "$env:APPDATA\.minecraft\resourcepacks\LipatantsArtefactsAssets" -Recurse; Copy-Item "..\LipatantsArtefactsAssets" "$env:APPDATA\.minecraft\resourcepacks\" -Recurse
