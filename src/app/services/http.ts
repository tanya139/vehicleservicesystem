import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth';

@Injectable({ providedIn: 'root' })
export class HttpService {

  private BASE = 'http://localhost:8081';

  constructor(private http: HttpClient, private auth: AuthService) {}

  private headers(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + (this.auth.getToken() || '')
    });
  }

  get(path: string) {
    return this.http.get(this.BASE + path, { headers: this.headers() });
  }

  post(path: string, body: any) {
    return this.http.post(this.BASE + path, body, { headers: this.headers() });
  }

  put(path: string, body: any = {}) {
    return this.http.put(this.BASE + path, body, { headers: this.headers() });
  }
}