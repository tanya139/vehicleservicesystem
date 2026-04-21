import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private TOKEN_KEY = 'jwt_token';
  private ROLE_KEY  = 'user_role';
  private USER_KEY  = 'username';

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  private isBrowser(): boolean {
    return isPlatformBrowser(this.platformId);
  }

  saveSession(token: string, role: string, username: string) {
    if (this.isBrowser()) {
      localStorage.setItem(this.TOKEN_KEY, token);
      localStorage.setItem(this.ROLE_KEY, role);
      localStorage.setItem(this.USER_KEY, username);
    }
  }

  getToken(): string | null {
    return this.isBrowser() ? localStorage.getItem(this.TOKEN_KEY) : null;
  }

  getRole(): string | null {
    return this.isBrowser() ? localStorage.getItem(this.ROLE_KEY) : null;
  }

  getUsername(): string | null {
    return this.isBrowser() ? localStorage.getItem(this.USER_KEY) : null;
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout() {
    if (this.isBrowser()) {
      localStorage.removeItem(this.TOKEN_KEY);
      localStorage.removeItem(this.ROLE_KEY);
      localStorage.removeItem(this.USER_KEY);
    }
  }
}