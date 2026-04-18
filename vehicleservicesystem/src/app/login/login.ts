import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { HttpService } from '../services/http';
import { AuthService } from '../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html'
})
export class LoginComponent {

  credentials = { username: '', password: '' };
  error = '';

  constructor(
    private http: HttpService,
    private auth: AuthService,
    private router: Router
  ) {}

  login() {
    this.http.post('/api/auth/login', this.credentials).subscribe({
      next: (res: any) => {
        this.auth.saveSession(res.token, res.role, res.username);
        this.redirectByRole(res.role);
      },
      error: () => this.error = 'Invalid credentials'
    });
  }

  redirectByRole(role: string) {
    if (role === 'OWNER')    this.router.navigate(['/vehicle']);
    if (role === 'ADMIN')    this.router.navigate(['/admin']);
    if (role === 'MECHANIC') this.router.navigate(['/mechanic']);
  }
}