from pathlib import Path

BASE_DIR = Path("/home/oscar/Área de trabalho/TCC-Artur-Jackson-e-Oscar-de-Brito--BaseMLCQ")
SNIPPETS_DIR = BASE_DIR / "sonar-trechos" / "generated-snippets"


def patch_6633402():
    path = SNIPPETS_DIR / "Snippet_6633402.java"
    text = path.read_text(encoding="utf-8")

    text = text.replace("public class Snippet__6633402 {\n\n    */", "public class Snippet__6633402 {\n\n")

    # fecha método/classe se faltar
    missing = text.count("{") - text.count("}")
    if missing > 0:
        text = text.rstrip() + ("\n}" * missing) + "\n"

    path.write_text(text, encoding="utf-8")


def patch_7070559():
    path = SNIPPETS_DIR / "Snippet_7070559.java"
    text = path.read_text(encoding="utf-8")

    missing = text.count("{") - text.count("}")
    if missing > 0:
        text = text.rstrip() + ("\n}" * missing) + "\n"

    path.write_text(text, encoding="utf-8")


def patch_8660303():
    path = SNIPPETS_DIR / "Snippet_8660303.java"
    text = path.read_text(encoding="utf-8")

    text = text.replace("public Snippet__8660303();", "// synthetic constructor call removed")
    text = text.replace("super();", "// synthetic super call removed")

    path.write_text(text, encoding="utf-8")

def main():
    patch_6633402()
    patch_7070559()
    patch_8660303()
    print("Patches aplicados nos 3 snippets restantes.")


if __name__ == "__main__":
    main()
