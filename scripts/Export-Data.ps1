Remove-Item "$env:APPDATA\.minecraft\saves\Lipatant's Artefacts\datapacks\LipatantsArtefactData" -Recurse
Copy-Item "..\LipatantsArtefactData" "$env:APPDATA\.minecraft\saves\Lipatant's Artefacts\datapacks\" -Recurse
