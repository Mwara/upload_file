from typing import Union, Optional, List
from pydantic import BaseModel
from datetime import datetime

class CallDeatailRecord(BaseModel): 
    record_date: datetime
    l_spc: Optional[int]
    l_ssn: Optional[int]
    l_ri: Optional[int]
    l_gt_i: Optional[int]
    l_gt_digits: Optional[str]
    r_spc: Optional[int]
    r_ssn: Optional[int]
    r_ri: Optional[int]
    r_gt_i: Optional[int]
    r_gt_digits: Optional[str]
    service_code: Optional[str]
    or_nature: Optional[int]
    or_plan: Optional[int]
    or_digits: Optional[str]
    de_nature: Optional[int]
    de_plan: Optional[int]
    de_digits: Optional[str]
    isdn_nature: Optional[int]
    isdn_plan: Optional[int]
    msisdn: Optional[str]
    vlr_nature: Optional[int]
    vlr_plan: Optional[int]
    vlr_digits: Optional[str]
    imsi: Optional[str]
    status: str
    type: str
    tstamp: datetime
    local_dialog_id : Optional[int]
    remote_dialog_id: Optional[int]
    dialog_duration: Optional[int]
    ussd_string: Optional[str]
    id: str
    file_id: int