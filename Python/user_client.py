import requests
from dataclasses import dataclass

# 🎯 DTOs inside the same file
@dataclass
class RequestDTO:
    user_id: int

@dataclass
class ResponseDTO:
    id: int
    name: str

@dataclass
class ErrorDTO:
    message: str
    code: int


class UserClient:
    def __init__(self, base_url: str):
        self.base_url = base_url

    def get_user(self, req: RequestDTO) -> ResponseDTO | ErrorDTO:
        try:
            response = requests.get(f"{self.base_url}/users/{req.user_id}")
            if response.status_code == 200:
                data = response.json()
                return ResponseDTO(id=data["id"], name=data["name"])
            else:
                return ErrorDTO(message="User not found", code=response.status_code)
        except Exception as e:
            return ErrorDTO(message=str(e), code=500)
