from typing import Union, Optional, List
from datetime import datetime

from fastapi import FastAPI, Request
from pydantic import BaseModel
from starlette import status
from fastapi.responses import JSONResponse

from .helpers.cdr_format import CallDeatailRecord
from .helpers.input_parameters import filters

from .dependencies import connect

app = FastAPI()

@app.get("/")
async def health():
    return JSONResponse(
        status_code=status.HTTP_200_OK,
        content={"message": "running"},
    ) 

@app.get(
    "/call_detail_records",
    status_code=status.HTTP_200_OK,
    response_model=List[CallDeatailRecord],
)
async def get_call_detail_records(
    request: Request,
    data: filters,
) -> List[CallDeatailRecord]:
    
    try:
        db_connection = await connect()

        call_detail_records = await db_connection.fetch(
            """
            SELECT *
            FROM call_detail_records cr
            WHERE record_date BETWEEN $1::TIMESTAMP AND $2::TIMESTAMP
            AND CASE WHEN $3::TEXT IS NULL AND $4::TEXT IS NULL THEN true
            WHEN $3::TEXT IS NULL THEN imsi = $4::TEXT
            WHEN $4::TEXT IS NULL THEN msisdn = $3::TEXT
            ELSE (msisdn = $3::TEXT OR imsi = $4::TEXT)
            END;
            """,
            data.record_date_start,
            data.record_date_end,
            data.msisdn,
            data.imsi,
        )

        return [
            CallDeatailRecord(**record) for record in call_detail_records
        ] if call_detail_records else []

    except Exception as e:
        print(e)
        return JSONResponse(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            content={"message": "Error getting call detail records"},
        )



