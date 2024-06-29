package com.dtaquito_backend.dtaquito_backend.suscriptions.application.internal.queryservices;

import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.aggregates.Suscriptions;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.entities.Plan;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.queries.GetAllSuscriptionsByPlanQuery;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.queries.GetSuscriptionsByIdQuery;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.model.valueObjects.PlanTypes;
import com.dtaquito_backend.dtaquito_backend.suscriptions.domain.services.SuscriptionsQueryService;
import com.dtaquito_backend.dtaquito_backend.suscriptions.infrastructure.persistance.jpa.PlanRepository;
import com.dtaquito_backend.dtaquito_backend.suscriptions.infrastructure.persistance.jpa.SuscriptionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuscriptionsQueryServiceImpl implements SuscriptionsQueryService {

    private final SuscriptionsRepository suscriptionsRepository;
    private final PlanRepository planRepository;

    public SuscriptionsQueryServiceImpl(SuscriptionsRepository suscriptionsRepository, PlanRepository planRepository) {
        this.suscriptionsRepository = suscriptionsRepository;
        this.planRepository = planRepository;
    }

    @Override
    public List<Suscriptions> handle(GetAllSuscriptionsByPlanQuery query) {
        Plan plan = planRepository.findByPlanType(PlanTypes.valueOf(query.plan()))
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        return suscriptionsRepository.findAllByPlanId(plan.getId());
    }

    @Override
    public Optional<Suscriptions> handle(GetSuscriptionsByIdQuery query) {
        return suscriptionsRepository.findById(query.id());
    }

    @Override
    public List<Suscriptions> getAllSuscriptions() {
        return suscriptionsRepository.findAll();
    }

    @Override
    public Optional<Suscriptions> getSuscriptionById(Long id) {
        return suscriptionsRepository.findById(id);
    }

    @Override
    public Optional<Suscriptions> getSubscriptionByUserId(Long userId) {
        return suscriptionsRepository.findByUserId(userId);
    }
}
