import { TestBed } from '@angular/core/testing';

import { PremiumServiceService } from './premium-service.service';

describe('PremiumServiceService', () => {
  let service: PremiumServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PremiumServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
