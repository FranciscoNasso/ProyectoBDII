import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  public API_URL = 'http://localhost:8080';

  constructor() { }
}
