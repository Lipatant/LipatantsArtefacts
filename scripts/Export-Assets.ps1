Remove-Item "$env:APPDATA\.minecraft\resourcepacks\LipatantsArtefactAssets" -Recurse; Copy-Item "..\LipatantsArtefactAssets" "$env:APPDATA\.minecraft\resourcepacks\" -Recurse
