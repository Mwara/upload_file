from typing import Union, Optional, List
from pydantic import BaseModel
from datetime import datetime


class filters(BaseModel):
    record_date_start: datetime
    record_date_end: datetime
    msisdn: Optional[str] = None
    imsi: Optional[str] = None