◇ToDoList

Spring Bootで作成したタスク管理アプリケーションです。

◇機能

- タスク一覧表示 - 登録されたタスクの確認
- タスク追加 - 新しいタスクの登録
- タスク編集 - 既存タスクの修正
- タスク削除 - 不要なタスクの削除
- ステータス管理 - タスクの状態管理
- 入力検証 - フォームのバリデーション機能

◇技術スタック

- Java: 17
- Spring Boot: 3.4.4
- データベース: MySQL 8.0
- ORM: Spring Data JPA / Hibernate
- テンプレートエンジン: Thymeleaf
- ビルドツール: Maven
- その他: Lombok, Spring Boot DevTools

◇画面構成

1. **タスク一覧画面** (`/`)
   - 登録されたタスクの表示
   - 編集・削除ボタン

2. **タスク追加画面** (`/create`)
   - 新規タスクの入力フォーム
   - タイトル、説明、ステータスの設定

3. **タスク編集画面** (`/todo/edit/{id}`)
   - 既存タスクの修正フォーム
   - バリデーション機能付き

◇主要機能の詳細

CRUD操作
- **Create**: タスクの新規作成
- **Read**: タスク一覧の表示
- **Update**: タスクの編集・更新
- **Delete**: タスクの削除

バリデーション
- 入力フォームのサーバーサイドバリデーション
- エラーメッセージの表示

フラッシュメッセージ
- 操作成功時の成功メッセージ
- エラー発生時のエラーメッセージ

このプロジェクトはSpring Bootの学習目的で作成しました。
