# 🛠️ Contributing to SkyStones Java Edition

Thanks for your interest in contributing! This project is a JavaFX-based game built with Gradle, and community help is welcome.

## 📅 Step-by-Step Setup

### 1. Fork or Clone the Repository

- Fork the repo using the GitHub UI\
  **OR**
- Clone it locally:
  ```bash
  git clone https://github.com/NicMineDude/Skystones-JAVA-Edition.git
  cd your-repo-name
  ```

### 2. Download Required Video Assets

This project uses external video files not included in the repository.

- 📦 **Download the video assets:**\
  Contact: NicMineDude on Discord. I will send it to you myself for download.

- 🌟 **Place the files in the correct folder:**\
  Place them in:

  ```
  /assets/
  ```

### 3. Set Up VM Options for JavaFX

To run the project successfully in your IDE (preferably IntelliJ), you must include the JavaFX modules via VM options.

#### In IntelliJ:

Go to `Run > Edit Configurations...` and add this to your VM options:

```
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media,java.desktop
```

> 🔁 Replace `/path/to/javafx-sdk/lib` with the actual path to your JavaFX installation.

If you're unsure where to download JavaFX:\
🖙 [https://openjfx.io](https://openjfx.io)

---

## ✅ Contribution Guidelines

- Follow the existing code style and naming conventions.
- Create a new branch for your feature or bug fix:
  ```bash
  git checkout -b feature/your-feature-name
  ```
- Commit with clear messages.
- Submit a Pull Request and describe your changes.

If you're adding a new feature or making a structural change, please open an issue first to discuss it.

---

Thank you for contributing! 🙏

