# --- ビルドステージ ---
FROM eclipse-temurin:24_36-jdk-alpine AS build

# ネットワーク関連のパッケージをインストール
RUN apk add --no-cache curl wget ca-certificates

# 作業ディレクトリを設定
WORKDIR /app

# Gradleラッパーと設定ファイルをコピー
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Gradleラッパーに実行権限を付与
RUN chmod +x ./gradlew

# 依存関係をダウンロード（複数のリポジトリとリトライ設定）
RUN ./gradlew dependencies --no-daemon --refresh-dependencies --stacktrace || \
    ./gradlew dependencies --no-daemon --refresh-dependencies --stacktrace || \
    ./gradlew dependencies --no-daemon --refresh-dependencies --stacktrace

# ソースコードをコピー
COPY src ./src

# アプリケーションをビルド
RUN ./gradlew bootJar -x test --no-daemon

# --- 実行ステージ ---
FROM eclipse-temurin:24-jre-alpine

# セキュリティのため非rootユーザーを作成
RUN addgroup -g 1000 appgroup && adduser -u 1000 -G appgroup -s /bin/sh -D appuser

# 作業ディレクトリを設定
WORKDIR /app

# ビルド成果物をコピー
COPY --from=build /app/build/libs/*.jar app.jar

# ファイルの所有者を変更
RUN chown -R appuser:appgroup /app

# 非rootユーザーに切り替え
USER appuser

# ポートを公開
EXPOSE 8080

# アプリケーションを実行
ENTRYPOINT ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]
