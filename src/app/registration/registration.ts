import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { HttpService } from '../services/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './registration.html'
})
export class RegistrationComponent {

  user = { username: '', password: '', email: '', role: 'OWNER' };
  message = '';

  constructor(private http: HttpService, private router: Router) {}

  register() {
    this.http.post('/api/auth/register', this.user).subscribe({
      next: () => {
        this.message = 'Registered! Please login.';
        this.router.navigate(['/login']);
      },
      error: (err: any) => this.message = err.error?.message || 'Registration failed'
    });
  }
}