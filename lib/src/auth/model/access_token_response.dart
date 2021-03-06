import 'package:json_annotation/json_annotation.dart';

part 'access_token_response.g.dart';

/// API response from https://kauth.kakao.com/oauth/token API
@JsonSerializable(fieldRename: FieldRename.snake, includeIfNull: false)
class AccessTokenResponse {
  /// <nodoc>
  AccessTokenResponse(this.accessToken, this.expiresIn, this.refreshToken,
      this.refreshTokenExpiresIn, this.scopes, this.tokenType);

  // access token
  String accessToken;

  // remaining time for the access token to expire in seconds
  int expiresIn;

  // token used for getting new access token via [AuthApi]
  String refreshToken;

  // remaining time for the refresh token to expire in seconds
  int refreshTokenExpiresIn;

  // list of scopes this user agreed to.
  // ex. "account_email story_publish"
  @JsonKey(name: "scope")
  String scopes;

  /// <nodoc>
  String tokenType;

  /// <nodoc>
  factory AccessTokenResponse.fromJson(Map<String, dynamic> json) =>
      _$AccessTokenResponseFromJson(json);

  /// <nodoc>
  Map<String, dynamic> toJson() => _$AccessTokenResponseToJson(this);
}
