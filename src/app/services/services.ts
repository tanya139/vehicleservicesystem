import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterLink } from '@angular/router';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './services.html'
})
export class ServicesComponent implements OnInit {

  serviceHistory: any[] = [];

  constructor(
    private http: HttpService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadHistory();
    }
  }

  loadHistory() {
    this.http.get('/api/owner/services').subscribe({
      next: (data: any) => {
        this.serviceHistory = [...data];
      },
      error: (err) => console.error('Failed to load service history', err)
    });
  }
}