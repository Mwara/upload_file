import os

import asyncpg

import dotenv

dotenv.load_dotenv()

async def connect():
    return await asyncpg.connect(
        host=os.getenv("DB_HOST"),
        database=os.getenv("DB_NAME"),
        user=os.getenv("DB_USER"),
        port=os.getenv("DB_PORT"),
        password=os.getenv("DB_PASSWORD"),
        ssl="disable",
    )
