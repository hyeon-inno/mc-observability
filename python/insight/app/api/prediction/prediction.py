from fastapi import APIRouter, Depends, Body
from config.ConfigManager import read_config_prediction
from app.api.prediction.request.req import Item, PredictionBody, PredictionPath, GetHistoryPath, GetPredictionHistoryQuery
from app.api.prediction.response.res import ResBodyPredictionOptions, PredictionOptions, ResBodyPredictionResult, \
    PredictionResult, PredictionHistory, ResBodyPredictionHistory
from app.api.prediction.description.description import get_options_description, post_prediction_description, get_history_description
from app.api.prediction.utils.utils import PredictionService
import pandas as pd

router = APIRouter()


@router.get(
    path='/predictions/options',
    description=get_options_description['api_description'],
    responses=get_options_description['responses']
)
async def get_prediction_options():
    config_data = read_config_prediction('config/prediction.ini')

    return ResBodyPredictionOptions(data=PredictionOptions(**config_data))


@router.post(
    path='/predictions/nsId/{nsId}/target/{targetId}',
    description=post_prediction_description['api_description'],
    responses=post_prediction_description['response']
)
async def predict_monitoring_data(
        body_params: PredictionBody,
        path_params: PredictionPath = Depends()
):
    prediction_service = PredictionService()
    df = prediction_service.get_data(path_params, body_params)
    result_dict = prediction_service.predict(df, path_params, body_params)

    prediction_result = PredictionResult(
        nsId=path_params.nsId,
        targetId=path_params.targetId,
        metric_type=body_params.metric_type,
        target_type=body_params.target_type,
        values=result_dict
    )

    return ResBodyPredictionResult(data=prediction_result)


@router.get(
    path='/predictions/nsId/{nsId}/target/{targetId}/history',
    description=get_history_description['api_description'],
    responses=get_history_description['response']
)
async def get_prediction_history(
        path_params: GetHistoryPath = Depends(),
        query_params: GetPredictionHistoryQuery = Depends()
):
    prediction_service = PredictionService()
    result_dict = prediction_service.get_prediction_history(path_params, query_params)

    prediction_history = PredictionHistory(
        nsId=path_params.nsId,
        targetId=path_params.targetId,
        metric_type=query_params.metric_type,
        values=result_dict
    )

    return ResBodyPredictionHistory(data=prediction_history)

