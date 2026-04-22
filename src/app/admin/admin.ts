import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html'
})
export class AdminComponent implements OnInit {

  requests: any[] = [];
  mechanicId: number = 0;
  message = '';

  constructor(
    private http: HttpService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.loadRequests();
    }
  }

  loadRequests() {
    this.http.get('/api/admin/requests').subscribe({
      next: (data: any) => {
        this.requests = [...data];
      },
      error: () => console.error('Failed to load requests')
    });
  }

  assignMechanic(serviceId: number) {
    this.http.put(`/api/admin/assign/${serviceId}?mechanicId=${this.mechanicId}`, {})
    .subscribe({
      next: () => {
        this.message = 'Mechanic assigned!';
        this.loadRequests();
      },
      error: () => this.message = 'Assignment failed'
    });
  }
}